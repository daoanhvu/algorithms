#include <thread>
#include <queue>
#include <condition_variable>
#include <sys/socket.h>
#include <netinet/in.h>
#include "nautilus_event.h"

namespace nserver {
    class NServer {
        private:
            // Network
            int serverFd;
            struct sockaddr_in address;            

            bool isRunning;
            bool done;
            std::queue<NautilusEvent> quEvents; 
            std::mutex queueMutex;

            // For signaling consumers and producers
            std::condition_variable condVar;
            
            
        public:
            void start() {

            }

            void stop() {

            }

            void startListening() {
                int item;
                char buffer[1024];

                serverFd = socket(AF_INET, SOCK_STREAM, 0);

                if (serverFd == 0) {
                    return;
                }

                while (isRunning) {
                    // Produce an item
                    item = rand() % 100;

                    {
                        // Lock the mutex
                        std::unique_lock<std::mutex> lock(mtx);

                        // Wait for space in the buffer
                        while (buffer.size() == BUFFER_SIZE) {
                            cond_var.wait(lock);
                        }

                        // Add item to the buffer
                        buffer.push(item);

                        // Signal a full slot
                        cond_var.notify_one();
                    }
                }
            }

            void _consumeEvent() {
                NautilusEvent item;
                while(isRunning) {
                    // Lock the mutext
                    std::unique_lock<std::mutex> lock(queueMutex);

                    // Wait for a full slot (or producer to finish)
                    while (quEvents.empty() && !done) {
                        condVar.wait(lock);
                    }

                    // Check if producer is done and no item left
                    if (done && quEvents.empty()) {
                        break;
                    }

                    // Remove item from the buffer
                    item = quEvents.front();
                    quEvents.pop();

                    condVar.notify_one();
                }
            }

    };
}
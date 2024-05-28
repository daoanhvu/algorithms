#include <iostream>
#include <chrono>
#include <queue>
#include <thread>
#include <condition_variable>

struct tagTask {
    int id;
    int value;
    int result;
};
typedef struct tagTask Task;

struct tagProducerConsumerParam {
    bool running;
    bool done;
    std::mutex runningMutex;
    std::queue<Task*> queue;
    std::mutex queueMutex;
    std::condition_variable conVar;
};
typedef struct tagProducerConsumerParam ProducerConsumerParam;

const int MAX_COUNT = 10;

void consumTask(ProducerConsumerParam *param) {

    std::unique_lock<std::mutex> lockRunning(param->runningMutex);
    bool shouldRun = param->running;
    lockRunning.unlock();
    Task *item;

    while(shouldRun) {
        {
            std::unique_lock<std::mutex> lock(param->queueMutex);

            while((param->queue).empty() && !param->done) {
                (param->conVar).wait(lock);
            }

            if (param->done && (param->queue).empty()) {
                break;
            }

            // Remove item from queue
            item = (param->queue).front();
            (param->queue).pop();

            //Signal an item slot
            (param->conVar).notify_one();
        }

        std::cout << "Consumed item: " << item->id << std::endl;
        delete item;

        std::unique_lock<std::mutex> lockRunning1(param->runningMutex);
        shouldRun = param->running;
        lockRunning1.unlock();
    }
}

void produceTask(ProducerConsumerParam *param) {

    std::unique_lock<std::mutex> lockRunning(param->runningMutex);
    bool shouldRun = param->running;
    lockRunning.unlock();

    Task *item;
    while (shouldRun) {
        item = new Task;
        item->id = rand();
        item->result = 0;

        {
            std::unique_lock<std::mutex> lock(param->queueMutex);

            while ((param->queue).size() == MAX_COUNT) {
                (param->conVar).wait(lock);
            }

            (param->queue).push(item);

            (param->conVar).notify_one();
        }

        std::cout << "Produced item: " << item->id << std::endl;

        std::unique_lock<std::mutex> lockRunning1(param->runningMutex);
        shouldRun = param->running;
        lockRunning1.unlock();
       
    }
}

int main(int argc, char** args) {

    using namespace std::chrono_literals;

    ProducerConsumerParam serverParam;

    // Set the server state to started
    serverParam.done = false;
    serverParam.running = true;

    std::thread producerThread(produceTask, &serverParam);
    std::thread consumerThread(consumTask, &serverParam);

    std::this_thread::sleep_for(2000ms);
    std::unique_lock<std::mutex> lockRunning1(serverParam.runningMutex);
    serverParam.running = false;
    lockRunning1.unlock();

    producerThread.join();
    consumerThread.join();

    return 0;
}
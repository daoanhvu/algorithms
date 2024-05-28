#include <iostream>
#include <cerrno>
#include <cstring>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

int main(int argc, char **args) {
    const int port = 1099;

    int serverSocketFd;
    int incomingSocket;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};
    std::string hello = "Hello from server.";

    // Create server socket file descriptor
    serverSocketFd = socket(AF_INET, SOCK_STREAM, 0);
    if (serverSocketFd == 0) {
        std::cerr << "Socket failed: " << std::strerror(errno) << std::endl;
        return -1;
    }

    //Forcefully attaching socket to the port
    if (setsockopt(serverSocketFd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        std::cerr << "Attaching socket to port failed: " << std::strerror(errno) << std::endl;
        return -2;
    }
    
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(port);

    // Binding the socket to the network address and port
    if (bind(serverSocketFd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        std::cerr << "Binding network address failed: " << std::strerror(errno) << std::endl;
        return -3;
    }
    int clientCount = 0;
    while (clientCount < 1) {
        if (listen(serverSocketFd, 3) < 0) {
            std::cerr << "Listen failed " << std::strerror(errno) << std::endl;
            break;
        }

        incomingSocket = accept(serverSocketFd, (struct sockaddr *)&address, (socklen_t *)&addrlen);
        if (incomingSocket < 0) {
            std::cerr << "Accept failed " << std::strerror(errno) << std::endl;
            break;
        }

        // Receiving a message from the client
        read(incomingSocket, buffer, 1024);
        std::cout << "Received: " << buffer << std::endl;

        send(incomingSocket, "stop", 4, 0);
        std::cout << "Sent message to client!\n";
    }

    close(incomingSocket);
    close(serverSocketFd);


    return 0;
}
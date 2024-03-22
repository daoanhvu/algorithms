#include <iostream>
#include <vector>
#include <pthread.h>
#include <semaphore.h>
#include <queue>

struct tagTask {
    int id;
    int value;
    int result;
};
typedef struct tagTask Task;

struct tagProducerConsumerParam {
    bool running;
    pthread_mutex_t running_mutex;
};
typedef struct tagProducerConsumerParam ProducerConsumerParam;

// Queue of task that need to be done by consumers
std::queue<Task*> tasks;
const int MAX_COUNT = 10;

// Count the number of task waiting in queue
sem_t full;
// Count the number of empty slot in queue
sem_t empty;
// Mutex for queue access
pthread_mutex_t mutex;

void *consumTask(void *p) {

    ProducerConsumerParam *param = (ProducerConsumerParam*)p;
    param->running = true;

    while(param->running) {
        // wait until at least a task inserted to queue
        sem_wait(&full);

        // Lock the queue
        pthread_mutex_lock(&mutex);

        // Pick the task from queue
        Task *aTask = tasks.front();
        tasks.pop();

        //Unlock the queue
        pthread_mutex_unlock(&mutex);

        //Signal the a task has been removed from queue, 
        //so there is one more free slot in the queue
        sem_post(&empty);

        // Now, process the task
        aTask->result = 1;
        delete aTask;
    }
    return nullptr;
}

void *produceTask(void *p) {

    ProducerConsumerParam *param = (ProducerConsumerParam*)p;
    param->running = true;

    while(param->running) {
        Task *aTask = new Task;
        aTask->id = rand();
        aTask->result = 0;

        sem_wait(&empty);

        pthread_mutex_lock(&mutex);

        tasks.push(aTask);

        pthread_mutex_unlock(&mutex);

        sem_post(&full);
    }

    return nullptr;
}

int main(int argc, char** args) {

    // Initialize semaphores
    sem_init(&empty, 0, MAX_COUNT);
    sem_init(&full, 0, 0);
    pthread_mutex_init(&mutex, nullptr);

    //Create producers and consumers
    pthread_t producer_thread1;
    pthread_t producer_thread2;
    pthread_t consumer_thread1;
    pthread_t consumer_thread2;

    ProducerConsumerParam p1;
    ProducerConsumerParam p2;
    ProducerConsumerParam c1;
    ProducerConsumerParam c2;

    pthread_mutex_init(&(p1.running_mutex), nullptr);
    pthread_create(&producer_thread1, nullptr, produceTask, (void*)&p1);

    pthread_mutex_init(&(c1.running_mutex), nullptr);
    pthread_create(&consumer_thread1, nullptr, consumTask, (void*)&c1);

    //Wait for threads get done their job
    pthread_join(producer_thread1, nullptr);
    pthread_join(consumer_thread1, nullptr);

    // Destroy 

    return 0;
}
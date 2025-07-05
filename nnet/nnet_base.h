#ifndef NNET_BASE_H
#define NNET_BASE_H

#include <stdexcept>

struct Tensor {
int batch_size;
int channels;
int height;
int width;
float *data; // Pointer to the data array

Tensor(int b, int c, int h, int w) : batch_size(b), channels(c), height(h), width(w) {
  data = new float[b * c * h * w]; // Allocate memory for the tensor data
}
~Tensor() {
  delete[] data; // Free the allocated memory
}

// Function to calculate the total number of elements in the tensor
int size() const {
  return batch_size * channels * height * width;
}

float& at(int b, int c, int h, int w) const {
  return data[(b * channels + c) * height * width + h * width + w];
}

float operator[](int index) const {
  return data[index];
}

Tensor& operator*(const Tensor& other) {
  if (batch_size != other.batch_size || channels != other.channels ||
    height != other.height || width != other.width) {
    throw std::invalid_argument("Tensors must have the same dimensions for multiplication.");
  }

  Tensor* result = new Tensor(batch_size, channels, height, width);
  for (int i = 0; i < size(); ++i) {
    result->data[i] = this->data[i] * other.data[i];
  }
  return *result;
}
};

#endif // NNET_BASE_H
#include <iostream>
#include <vector>
#include <stack>

int main(int argc, char *argv[]) {
  if (argc < 2) {
    std::cerr << "Usage: " << argv[0] << " <number>" << std::endl;
    return 1;
  }
  int n = std::stoi(argv[1]);
  return 0;
}
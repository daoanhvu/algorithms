#include <iostream>

int main() {
  int n;
  std::cout << "Enter a number: ";
  std::cin >> n;

  int max_gap = 0;
  int current_gap = 0;
  bool counting = false;

  while (n > 0) {
    if (n & 1) { // Check if the last bit is 1
      if (counting) {
        max_gap = std::max(max_gap, current_gap);
      }
      counting = true; // Start counting after the first 1
      current_gap = 0; // Reset gap count
    } else {
      if (counting) {
        current_gap++; // Increment gap count
      }
    }
    n >>= 1; // Shift right to process the next bit
  }

  std::cout << "Maximum binary gap: " << max_gap << std::endl;
  return 0;
}

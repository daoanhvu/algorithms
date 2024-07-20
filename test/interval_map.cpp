#include <map>
#include <iostream>

using namespace std;

template<typename K, typename V>
class interval_map {
	friend void IntervalMapTest();
	V m_valBegin;
	std::map<K,V> m_map;
	public:
		// constructor associates whole range of K with val
		interval_map(V const& val)
		: m_valBegin(val)
		{}

		// Assign value val to interval [keyBegin, keyEnd).
		// Overwrite previous values in this interval.
		// Conforming to the C++ Standard Library conventions, the interval
		// includes keyBegin, but excludes keyEnd.
		// If !( keyBegin < keyEnd ), this designates an empty interval,
		// and assign must do nothing.
		void assign( K const& keyBegin, K const& keyEnd, V const& val ) {
			// INSERT YOUR SOLUTION HERE
			if(!(keyBegin < keyEnd)) {
				return;
			}

			if(m_map.empty()) {
				if(val != m_valBegin) {
					m_map[keyBegin] = val;
					m_map[keyEnd] = m_valBegin;
				}
				return;
			}


			map<K,V>::iterator it = m_map.begin();
			K firstKey = it->first;
			if (keyBegin < firstKey) {
				if(keyEnd < firstKey || !( keyBegin < firstKey || firstKey < keyEnd )) {
					//
					if(val == m_valBegin) {
						// Do nothing here
						return;
					}

					m_map[firstKey] = val;
					if(keyEnd < firstKey) {
						m_map[keyEnd] = m_valBegin;
					} else {
						m_map.erase(it);
					}
					return;
				}
			} else {
				auto bIt = m_map.lower_bound(keyBegin);
				auto eIt = m_map.upper_bound(keyEnd);

				if(bIt != m_map.end()) {
					m_map.erase(bIt, m_map.end());
				}
				m_map[keyBegin] = val;
				m_map[keyEnd] = m_valBegin;
			}
		}

		// look-up of the value associated with key
		V const& operator[]( K const& key ) const {
			auto it=m_map.upper_bound(key);
			if(it==m_map.begin()) {
				return m_valBegin;
			} else {
				return (--it)->second;
			}
		}

		void printMap() {
			map<K,V>::iterator it = m_map.begin();
			cout << std::endl;
			while(it != m_map.end() ) {
				cout << "("<< it->first <<", "<< it->second <<"" <<") ";
				it++;
			}
			cout << std::endl;
		}
};

void IntervalMapTest() {

}

// Many solutions we receive are incorrect. Consider using a randomized test
// to discover the cases that your implementation does not handle correctly.
// We recommend to implement a test function that tests the functionality of
// the interval_map, for example using a map of int intervals to char.

int main(int argc, char ** args) {
	std::cout << "Hello" << std::endl;
	interval_map<int, char> testMap('a');
	// testMap.assign(0, 3, 'b');
	// testMap.assign(-3, 0, 'b');

	// testMap.assign(0, 3, 'b');
	// testMap.assign(-3, 1, 'b');

	testMap.assign(0, 3, 'b');
	testMap.assign(1, 4, 'c');

	testMap.printMap();
	return 0;
}
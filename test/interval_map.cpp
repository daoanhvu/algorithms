#include <map>
#include <iostream>


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
				}
				return;
			}


			std::map<K,V>::iterator it = m_map.begin();
			K firstKey = it->first;
			if (keyBegin < firstKey) {
				if(keyEnd < firstKey) {
					//
					if(val == m_valBegin) {
						// Do nothing here
						return;
					}

					m_map[firstKey] = val;
					m_map[keyEnd] = m_valBegin;
					
				} else {
					auto eIt = m_map.upper_bound(keyEnd);	
					if( == firstKey) {
						m_map.erase(it);
						m_map[firstKey] = val;
						return;
					}


					auto it = m_map.find(keyEnd);
					if(it == m_map.end()) {
						it = m_map.upper_bound(keyEnd);
					}
				}
			} else {
				auto bIt = m_map.lower_bound(keyBegin);
				auto eIt = m_map.upper_bound(keyEnd);
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
			std::map<K,V>::iterator it = m_map.begin();
			std::cout << std::endl;
			while(it != m_map.end() ) {
				std::cout << "("<< it->first <<", "<< it->second <<"" <<") ";
				it++;
			}
			std::cout << std::endl;
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
	testMap.assign(0, 3, 'b');
	testMap.assign(-3, 0, 'c');
	testMap.printMap();
	return 0;
}
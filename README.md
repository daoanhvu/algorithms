cl /std:c++17 /I header\ /c /Fo:bin\quick_sort.obj sorts\quick_sort.cpp

cl /std:c++17 /SUBSYSTEM:CONSOLE /I:header\  /Fe:bin\test.exe test.cpp bin\quick_sort.obj
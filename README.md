cl /std:c++17 /I header\ /c /Fo:bin\quick_sort.obj sorts\quick_sort.cpp

cl /std:c++17 /SUBSYSTEM:CONSOLE /I:header\  /Fe:bin\test.exe test.cpp bin\quick_sort.obj

cl /std:c++17 /SUBSYSTEM:CONSOLE /Fo:bin\count_notifications.obj /Fe:bin\count_notifications.exe count_notifications.cpp

cl /std:c++17 /SUBSYSTEM:CONSOLE /Fo:bin\roman_numbers.obj /Fe:bin\roman_numbers.exe roman_numbers.cpp
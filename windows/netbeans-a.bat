@echo on

rem Here set your netbeans folder
set netbeans_home=D:\DATA.IDB\Work\Java\netbeans
rem Here set your future netbeans config folder
set netbeans_config=D:\DATA.IDB\Work\Java\.nbconfig
rem Here set your jdk folder
set jdk_home=D:\DATA.IDB\Work\Java\jdk1.8.0_60
set PATH=%netbeans_home%\bin;%jdk_home%\bin;%PATH%
netbeans --userdir %netbeans_config% --jdkhome %jdk_home% --console suppress


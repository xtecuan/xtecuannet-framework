#!/bin/sh

# Here set your netbeans folder
export netbeans_home=/home/xtecuan/Java/Springboot-tools/netbeans
# Here set your future netbeans config folder
export netbeans_config=/home/xtecuan/Java/Springboot-tools/.nbconfig
# Here set your jdk folder
export jdk_home=/home/xtecuan/Java/Springboot-tools/jdk1.8.0_60
export PATH=$netbeans_home/bin:$jdk_home/bin:$PATH
netbeans --userdir $netbeans_config --jdkhome $jdk_home --nosplash
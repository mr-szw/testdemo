#!/bin/bash
echo "Running command : '$1' on services"
commandRun=$1

if [[ "$commandRun" =~ "test" ]];then
  echo "ascacas"
fi
#if [ $commandRun = ''];then
#  echo "you can use : start,stop,status commands"
#else
#  bash ./apcahe-zookeeper/bin/zkServer.sh $commandRun ./apcahe-zookeeper/conf/zoo-1.cfg
#  bash ./apcahe-zookeeper/bin/zkServer.sh $commandRun ./apcahe-zookeeper/conf/zoo-2.cfg
#  bash ./apcahe-zookeeper/bin/zkServer.sh $commandRun ./apcahe-zookeeper/conf/zoo-3.cfg
#fi


echo "Running Finish !!!"
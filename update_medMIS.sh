result=`docker pull zhongy95/medmis:latest`
time=$(date "+%Y%m%d-%H%M%S")
echo $time >> /home/ml23/lyh/log.log
echo $result >> /home/ml23/lyh/log.log
update_result="up to date"
if [[ ${result} != *${update_result}* ]]
then
        image_id=`docker images -f "dangling=true" -q`
	container_id=`docker ps -f "name=running_medmis" -q`
	echo "image id: "$image_id >> /home/ml23/lyh/log.log
	echo "container id: "$container_id >> /home/ml23/lyh/log.log
	echo "已获取所有id，开始更新" >> /home/ml23/lyh/log.log
	stop1=`docker stop $container_id`
	stop2=`docker rm $container_id`
	stop3=`docker rmi $image_id`
	stop=$stop1$stop2$stop3
        start=`docker run -d --network=host --name=running_medmis zhongy95/medmis:latest`
	echo $stop >> /home/ml23/lyh/log.log
	echo "新的container id: "$start >> /home/ml23/lyh/log.log
else 
	echo "没有更新" >> /home/ml23/lyh/log.log
fi

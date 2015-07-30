package com.cloud.schedule;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerChannel;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.admin.model.Notification;
import com.cloud.admin.model.Type;
import com.cloud.service.TaskService;
import com.cloud.service.TaskServiceFactory;

@Singleton // Specifies to Spring that this is a singleton
@Named // Spring looks for this annotation when scanning the classes to determine if it's a spring bean
@Service // 
public class RunMeTask {
	
	@Autowired
	protected TaskServiceFactory taskServiceF;
	 
	@Inject
	public BayeuxServer bayeuxServer;
	@Session
	public LocalSession sender;


	public  TaskService InstanceService(){
		return taskServiceF.getTaskService();
	}

	 
	public void printMe() {
		 
            // Create the channel name using the stock symbol
            String channelName = "/stock/" + Type.NOTIFICATION_TASK;
            
            // Initialize the channel, making it persistent and lazy
            bayeuxServer.createIfAbsent(channelName, new ConfigurableServerChannel.Initializer()
            {
                public void configureChannel(ConfigurableServerChannel channel)
                {
                    channel.setPersistent(true);
                    channel.setLazy(true);
                }
            });

            // Convert the Update business object to a CometD-friendly format
            Map<String, Object> data = new HashMap<String, Object>(4);
            data.put("symbol", Type.NOTIFICATION_TASK);
            data.put("oldValue", new Notification());
            data.put("newValue", new Notification(Type.NOTIFICATION_TASK));

            // Publish to all subscribers
            ServerChannel channel = bayeuxServer.getChannel(channelName);
            channel.publish(sender, data, null);
            System.out.println("Success " + channelName);
		
	}



	public TaskServiceFactory getTaskServiceF() {
		return taskServiceF;
	}



	public void setTaskServiceF(TaskServiceFactory taskServiceF) {
		this.taskServiceF = taskServiceF;
	}
}
package zheng.com;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AtyUsingNotification extends Activity {
	
	private Button btnNotification;
	NotificationManager notificationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_notification);
		
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(R.layout.aty_notification);
		
		btnNotification = (Button) findViewById(R.id.btnNotification);		
		btnNotification.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Notification notification = new Notification(R.drawable.ic_launcher,"zheng notification",System.currentTimeMillis());
				
				notification.setLatestEventInfo(AtyUsingNotification.this, "title", "content",
						PendingIntent.getActivity(AtyUsingNotification.this, 1, getIntent(), 0));
				
				notificationManager.notify(R.layout.aty_notification, notification);
				
			}
		});
		
	}

}
















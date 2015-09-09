package zheng.com;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		XmlResourceParser parser = getResources().getXml(R.xml.user);
		
		try {
			while (parser.getEventType() == XmlResourceParser.END_DOCUMENT) {
				if (parser.getEventType() == XmlResourceParser.START_TAG) {
					
					String name = parser.getAttributeValue(null, "name");
					String age = parser.getAttributeValue(null,"age");
					
					System.out.println(String.format("name is %s,age is %s", name,age));
				}
				
				parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

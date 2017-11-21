package jeongari.com.android_parsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;


public class MainActivity extends AppCompatActivity {


    TextView Title;
    TextView Date;
    TextView Reporter;
    TextView ContentText;
    Button getActionBtn;
    Runnable task;
    String html;

    String givenUrl = "http://news.joins.com/article/22123706";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ContentText = (TextView) findViewById(R.id.ContentText);
        Title = (TextView) findViewById(R.id.Title);
        Date = (TextView) findViewById(R.id.Date);
        Reporter = (TextView) findViewById(R.id.Reporter);
        getActionBtn = (Button) findViewById(R.id.getActionBtn);
        getActionBtn.setOnClickListener(mClickListener);


        task = new Runnable(){

            public void run(){

                html = getHtmltoText(givenUrl);

            }

        };

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            Thread thread = new Thread(task);
            thread.start();

            try{

                thread.join();  // 쓰레드 작업 끝날때까지 다른 작업들은 대기

            }catch(Exception e){



            }
            //Title.setText(html);
            //Date.setText(html);
            //Reporter.setText(html);
            ContentText.setText(html);



        }
    };

    public String getHtmltoText(String sourceUrlString) {
        List<Element> contents = null;

        String subject ="";
        String date ="";
        String reporter="";
        String content ="";
//        String texts[] = { subject, date, reporter, content};


        try {

             URL sUrl = new URL(sourceUrlString);
             InputStream is = sUrl.openStream();
             Source source= new Source(new InputStreamReader(is,"utf-8"))

            // 전체 소스 구문을 분석한다.
            source.fullSequentialParse();
            // HTML markup에서 text contents만 가져와서 스트링으로 변환한다.
            contents=source.getAllElements(HTMLElementName.BODY);




            for(int i =0; i<contents.size();i++)
            {
                Element element = (Element) contents.get(i);
                String classname = element.getAttributeValue("class");
                if (classname == "article_body mgfs4") {
                    String text = element.getTextExtractor().toString();
                    content += text;
                } else if (classname == "subject") {
                    String text = element.getTextExtractor().toString();
                    subject += text;
                } else if (classname == "journalist_area") {
                    String text = element.getTextExtractor().toString();
                    reporter += text;
                } else if (classname == "clearfx") {
                    String text = element.getTextExtractor().toString();
                    date += text;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


}

package com.baidu.tts;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.baidu.tts.answer.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.example.prefer.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ttstest extends Activity implements OnClickListener, SpeechSynthesizerListener {
	private Button tts_start;//��ʼ���Ű�ť---��
	private EditText minput;//����Ҫtts��������Ϣ----��
	private SpeechSynthesizer mSpeechSynthesizer;//TTSʵ��----��
	private String mSampleDirPath;
    private static final String SAMPLE_DIR_NAME = "preferTTS";
    private static final String SPEECH_FEMALE_MODEL_NAME = "bd_etts_speech_female.dat";
    private static final String SPEECH_MALE_MODEL_NAME = "bd_etts_speech_male.dat";
    private static final String TEXT_MODEL_NAME = "bd_etts_text.dat";
    private static final String LICENSE_FILE_NAME = "temp_license";
    private static final String ENGLISH_SPEECH_FEMALE_MODEL_NAME = "bd_etts_speech_female_en.dat";
    private static final String ENGLISH_SPEECH_MALE_MODEL_NAME = "bd_etts_speech_male_en.dat";
    private static final String ENGLISH_TEXT_MODEL_NAME = "bd_etts_text_en.dat";
	@Override
	public void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.tts_test);
		 //�������
		 initEvn();
		 //�ؼ��ĳ�ʼ������
		 initview();
		 //TTS���õĳ�ʼ��
		 inittts();
	}
	/////////�������///
	private void initEvn(){
		if (mSampleDirPath == null) {//���mSampleDirPath·��Ϊ�գ���ʼ����·��
			//Toast.makeText(getApplicationContext(), "û�и��ļ�", Toast.LENGTH_LONG).show();
			String sdcardPath=Environment.getExternalStorageDirectory().toString();
			mSampleDirPath=sdcardPath+"/"+SAMPLE_DIR_NAME;//����msample��·��
		}
		else{
			
			//Toast.makeText(getApplicationContext(), "�и��ļ�", Toast.LENGTH_LONG).show();
			makeDir(mSampleDirPath);
			//��ʼ����bat�ļ���sdcard����
			 copyFromAssetsToSdcard(false, SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/" + SPEECH_FEMALE_MODEL_NAME);
			 copyFromAssetsToSdcard(false, SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/" + SPEECH_MALE_MODEL_NAME);
			copyFromAssetsToSdcard(false, TEXT_MODEL_NAME, mSampleDirPath + "/" + TEXT_MODEL_NAME);
			copyFromAssetsToSdcard(false, LICENSE_FILE_NAME, mSampleDirPath + "/" + LICENSE_FILE_NAME);
			copyFromAssetsToSdcard(false, "english/" + ENGLISH_SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/"
			        + ENGLISH_SPEECH_FEMALE_MODEL_NAME);
			copyFromAssetsToSdcard(false, "english/" + ENGLISH_SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/"
			        + ENGLISH_SPEECH_MALE_MODEL_NAME);
			copyFromAssetsToSdcard(false, "english/" + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath + "/"
			        + ENGLISH_TEXT_MODEL_NAME);
		}
		
	}
	//���·�����Assets������ļ�������sdcard����
private void copyFromAssetsToSdcard(boolean isCover, String source, String dest) {
		// TODO Auto-generated method stub
	File file=new File(dest);
	if(isCover || (!isCover && !file.exists())){
		InputStream is=null;
		FileOutputStream fos=null;
        try {
            is = getResources().getAssets().open(source);
            String path = dest;
            fos = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = is.read(buffer, 0, 1024)) >= 0) {
                fos.write(buffer, 0, size);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
		
	}
/////////�ж��ļ��Ƿ��У�û�еĻ�����һ��
	private void makeDir(String mSampleDirPath2) {
		// TODO Auto-generated method stub
		File file=new File(mSampleDirPath2);
		if(!file.exists()){//����ļ�������
			file.mkdirs();
		}
	}
	////////////////////////////////////////ǰ�ڹ�����׼��
	//////////�ؼ���ʼ������//////
	private void initview() {
	//tts��ʼ����ؼ��ĳ�ʼ��----��
	tts_start=(Button) findViewById(R.id.tts_start);
	tts_start.setOnClickListener(this);
	
	//�������ֵ��ı���ĳ�ʼ������
	minput=(EditText) findViewById(R.id.minput);
		
	}
	//�ٶ�TTS�ĳ�ʼ��
    private void inittts() {
		// TODO Auto-generated method stub
    	//��ȡttsʵ��---��
        this.mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        //����APP�������ģ������ѡ�---��
        this.mSpeechSynthesizer.setContext(this);
        //����tts������---��
        //�ٶ��ĵ������mSpeechSynthesizer.setSpeechSynthesizerListener(SpeechSynthesizerListener)
        //�����Ǽ̳иýӿڣ�����ʹ��this
        this.mSpeechSynthesizer.setSpeechSynthesizerListener(this);
        
        
        //�ı�ģ���ļ�·������������ʹ�ã�
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, mSampleDirPath + "/"
                + TEXT_MODEL_NAME);
        // ��ѧģ���ļ�·������������ʹ�ã�
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, mSampleDirPath + "/"
                + SPEECH_FEMALE_MODEL_NAME);
        // �� �� �� Ȩ �� �� · �� , �� δ �� �� �� ʹ �� Ĭ �� · �� . �� �� �� ʱ �� Ȩ �� �� · �� ��
      //  LICENCE_FILE_NAME ���滻����ʱ��Ȩ�ļ���ʵ��·��������ʹ����ʱ license �ļ�ʱ��Ҫ����
     //   ���ã������[Ӧ�ù���]�п�ͨ��������Ȩ������Ҫ���øò��������齫���д���ɾ�����������棩
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_LICENCE_FILE, mSampleDirPath + "/"
                + LICENSE_FILE_NAME);
        
        // ���滻Ϊ����������ƽ̨��ע��Ӧ�õõ��� App ID (������Ȩ)
      //  this.mSpeechSynthesizer.setAppId("your_app_id");
        this.mSpeechSynthesizer.setAppId("7489219");
        
        // ���滻Ϊ����������ƽ̨ע��Ӧ�õõ��� apikey �� secretkey (������Ȩ)
       // this.mSpeechSynthesizer.setApiKey("your_api_key", "your_secret_key");
        this.mSpeechSynthesizer.setApiKey("5yE3broGYkNCFqi69ooOgioP", "dde23d1b447480c3d794da9d8eddb622");

     // ��Ȩ���ӿ�
        AuthInfo authInfo = this.mSpeechSynthesizer.auth(TtsMode.MIX);
        
        if (authInfo.isSuccess()) {
           // toPrint("auth success");
            this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, SpeechSynthesizer.SPEAKER_FEMALE);
           
            //�����ʼ���ӿ�
            mSpeechSynthesizer.initTts(TtsMode.MIX);
            int result =
                    mSpeechSynthesizer.loadEnglishModel(mSampleDirPath + "/" + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath
                            + "/" + ENGLISH_SPEECH_FEMALE_MODEL_NAME);
           // toPrint("loadEnglishModel result=" + result);
        } else {
            String errorMsg = authInfo.getTtsError().getDetailMessage();
           // toPrint("auth failed errorMsg=" + errorMsg);
        }
	}
	//�̳нӿ���Ҫ����Ķ���

///////////////////////�̳�SpeechSynthesizerListener����ӿڳ��ֵķ���
	@Override
	public void onError(String arg0, SpeechError arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpeechFinish(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpeechProgressChanged(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpeechStart(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSynthesizeDataArrived(String arg0, byte[] arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSynthesizeFinish(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSynthesizeStart(String arg0) {
		// TODO Auto-generated method stub
		
	}
///////////////////////�̳�SpeechSynthesizerListener����ӿڳ��ֵķ���
	////////////�̳�onclick�ӿ���Ҫʵ�ֵĶ���///////////
	@Override
	public void onClick(View v){
		int id=v.getId();//��ȡ�ؼ���ID
		switch(id){
		case R.id.tts_start:
			speak();
			break;
		default :break;
		
		}
	}
	/////////�̳иýӿ���Ҫд�ķ���//////
	private void speak() {
		// TODO Auto-generated method stub
		//��ȡEdittext���������
        String text = this.minput.getText().toString();
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
		//���ֲ���
        int result=this.mSpeechSynthesizer.speak(text);
        if(result<0){
        	Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
        }
	}
	
}


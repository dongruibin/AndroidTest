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
	private Button tts_start;//开始播放按钮---董
	private EditText minput;//输入要tts的文字信息----董
	private SpeechSynthesizer mSpeechSynthesizer;//TTS实例----董
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
		 //环境检测
		 initEvn();
		 //控件的初始化调用
		 initview();
		 //TTS调用的初始化
		 inittts();
	}
	/////////环境检测///
	private void initEvn(){
		if (mSampleDirPath == null) {//如果mSampleDirPath路径为空，则开始创建路径
			//Toast.makeText(getApplicationContext(), "没有该文件", Toast.LENGTH_LONG).show();
			String sdcardPath=Environment.getExternalStorageDirectory().toString();
			mSampleDirPath=sdcardPath+"/"+SAMPLE_DIR_NAME;//设置msample的路径
		}
		else{
			
			//Toast.makeText(getApplicationContext(), "有该文件", Toast.LENGTH_LONG).show();
			makeDir(mSampleDirPath);
			//开始拷贝bat文件到sdcard里面
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
	//以下方法将Assets里面的文件拷贝到sdcard里面
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
/////////判断文件是否有，没有的话创建一下
	private void makeDir(String mSampleDirPath2) {
		// TODO Auto-generated method stub
		File file=new File(mSampleDirPath2);
		if(!file.exists()){//如果文件不存在
			file.mkdirs();
		}
	}
	////////////////////////////////////////前期工作的准备
	//////////控件初始化设置//////
	private void initview() {
	//tts起始开光控件的初始化----董
	tts_start=(Button) findViewById(R.id.tts_start);
	tts_start.setOnClickListener(this);
	
	//输入文字的文本框的初始化工作
	minput=(EditText) findViewById(R.id.minput);
		
	}
	//百度TTS的初始化
    private void inittts() {
		// TODO Auto-generated method stub
    	//获取tts实例---董
        this.mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        //设置APP的上下文（必须的选项）---董
        this.mSpeechSynthesizer.setContext(this);
        //设置tts监听器---董
        //百度文档里面的mSpeechSynthesizer.setSpeechSynthesizerListener(SpeechSynthesizerListener)
        //这里是继承该接口，所以使用this
        this.mSpeechSynthesizer.setSpeechSynthesizerListener(this);
        
        
        //文本模型文件路径（离线引擎使用）
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, mSampleDirPath + "/"
                + TEXT_MODEL_NAME);
        // 声学模型文件路径（离线引擎使用）
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, mSampleDirPath + "/"
                + SPEECH_FEMALE_MODEL_NAME);
        // 本 地 授 权 文 件 路 径 , 如 未 设 置 将 使 用 默 认 路 径 . 设 置 临 时 授 权 文 件 路 径 ，
      //  LICENCE_FILE_NAME 请替换成临时授权文件的实际路径，仅在使用临时 license 文件时需要进行
     //   设置，如果在[应用管理]中开通了离线授权，不需要设置该参数，建议将该行代码删除（离线引擎）
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_LICENCE_FILE, mSampleDirPath + "/"
                + LICENSE_FILE_NAME);
        
        // 请替换为语音开发者平台上注册应用得到的 App ID (离线授权)
      //  this.mSpeechSynthesizer.setAppId("your_app_id");
        this.mSpeechSynthesizer.setAppId("7489219");
        
        // 请替换为语音开发者平台注册应用得到的 apikey 和 secretkey (在线授权)
       // this.mSpeechSynthesizer.setApiKey("your_api_key", "your_secret_key");
        this.mSpeechSynthesizer.setApiKey("5yE3broGYkNCFqi69ooOgioP", "dde23d1b447480c3d794da9d8eddb622");

     // 授权检测接口
        AuthInfo authInfo = this.mSpeechSynthesizer.auth(TtsMode.MIX);
        
        if (authInfo.isSuccess()) {
           // toPrint("auth success");
            this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, SpeechSynthesizer.SPEAKER_FEMALE);
           
            //引擎初始化接口
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
	//继承接口需要处理的东西

///////////////////////继承SpeechSynthesizerListener这个接口出现的方法
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
///////////////////////继承SpeechSynthesizerListener这个接口出现的方法
	////////////继承onclick接口需要实现的东西///////////
	@Override
	public void onClick(View v){
		int id=v.getId();//获取控件的ID
		switch(id){
		case R.id.tts_start:
			speak();
			break;
		default :break;
		
		}
	}
	/////////继承该接口需要写的方法//////
	private void speak() {
		// TODO Auto-generated method stub
		//获取Edittext里面的文字
        String text = this.minput.getText().toString();
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
		//文字播放
        int result=this.mSpeechSynthesizer.speak(text);
        if(result<0){
        	Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
        }
	}
	
}


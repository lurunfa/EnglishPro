package com.bupt.english.techer_cjcx;
/*
 * 播放媒体
 */

import com.bupt.english.main.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class VideoPlay extends Activity implements OnClickListener {
	private Button bt_play;
	private Button bt_pause;
	private Button bt_stop;
	private int currentPosition;

	private Button bt_replay;
	private SurfaceView sv_vedio;
	private MediaPlayer mediaplayer;
	private SeekBar sb_progress;
	private boolean flag;
	private TextView tv_time;
	private int time;
	private Button btn_back;
	String videoPath;
private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoplay);
		Intent intent = getIntent();
		videoPath = intent.getStringExtra("path");
		System.out.println(videoPath);
		//ed_path = (EditText) findViewById(R.id.ed_path);
		btn_back = (Button) findViewById(R.id.bt_back);
		bt_play = (Button) findViewById(R.id.bt_play);
		bt_pause = (Button) findViewById(R.id.bt_pause);
		bt_stop = (Button) findViewById(R.id.bt_stop);
		bt_replay = (Button) findViewById(R.id.bt_replay);
		bt_replay = (Button) findViewById(R.id.bt_replay);
		tv_time = (TextView) findViewById(R.id.tv_time);
		handler=new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what){
				case 0:
					int a=msg.getData().getInt("1");
					int b=msg.getData().getInt("2");
					set(b,a);
				}
			}
			private void set(int progress, int max) {
				// TODO 自动生成的方法存根
				tv_time.setText(toTime(progress) + "/"
						+ toTime(max));
			}

			private String toTime(int progress) {
				// TODO 自动生成的方法存根
				StringBuffer sb = new StringBuffer();
				int s = (progress / 1000) % 60;
				int m = progress / 60000;
				sb.append(m).append(":");
				if (s < 10) {
					sb.append(0);
				}
				sb.append((progress / 1000) % 60);
				return sb.toString();
			}
		};
		
		bt_pause.setEnabled(false);
		bt_stop.setEnabled(false);
		sb_progress = (SeekBar) findViewById(R.id.sb_progress);
		sb_progress.setEnabled(false);
		sb_progress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO 自动生成的方法存根
				if (mediaplayer != null) {
					int progress = seekBar.getProgress();
					mediaplayer.seekTo(progress);

				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO 自动生成的方法存根

			}
		});
		sv_vedio = (SurfaceView) findViewById(R.id.sv_video);
		 android.view.ViewGroup.LayoutParams lp = sv_vedio.getLayoutParams();
	        //lp.width =720;
	        lp.height =480;
	        sv_vedio.setLayoutParams(lp);
		sv_vedio.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		sv_vedio.getHolder().addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO 自动生成的方法存根
				System.out.println("销毁了");
				if (mediaplayer != null && mediaplayer.isPlaying()) {
					currentPosition = mediaplayer.getCurrentPosition();
				}
				stop();
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO 自动生成的方法存根
				System.out.println("创建了");
				if (currentPosition > 0) {
					play(currentPosition);
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO 自动生成的方法存根
				System.out.println("大小改变了");
			}
		});
		bt_play.setOnClickListener(this);
		bt_pause.setOnClickListener(this);
		bt_stop.setOnClickListener(this);
		bt_replay.setOnClickListener(this);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.bt_play:
			play(0);
			break;
		case R.id.bt_pause:
			pause();
			break;
		case R.id.bt_stop:
			stop();
			break;
		case R.id.bt_replay:
			replay();
			break;
		case R.id.bt_back:
			
			setResult(1);
			finish();
			break;
		}
	}
	

	private void replay() {
		// TODO 自动生成的方法存根
		if (mediaplayer != null && mediaplayer.isPlaying()) {
			mediaplayer.seekTo(0);
		} else {
			play(0);
		}
	}

	private void stop() {
		// TODO 自动生成的方法存根
		if (mediaplayer != null) {
			mediaplayer.stop();
			mediaplayer.release();
			mediaplayer = null;
			bt_play.setEnabled(true);
			bt_pause.setEnabled(false);
			bt_stop.setEnabled(false);
			flag = false;
			bt_pause.setText("暂停");
		} else {
			Toast.makeText(this, "请先播放音频！", 1).show();
		}
	}

	private void pause() {
		// TODO 自动生成的方法存根
		if (bt_pause.getText().toString().trim().equals("继续")) {
			mediaplayer.start();
			bt_pause.setText("暂停");
			return;
		} else {
			if (mediaplayer != null && mediaplayer.isPlaying()) {
				mediaplayer.pause();
				bt_pause.setText("继续");
			} else {
				Toast.makeText(this, "返回", 1).show();
			}
		}
	}
	VideoView videoView;
	
	

	private void play(final int currentPosition2) {
		// TODO 自动生成的方法存根
		//String path = ed_path.getText().toString().trim();
		//File file = new File(path);

		try {
			mediaplayer = new MediaPlayer();
			
			mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaplayer.setDisplay(sv_vedio.getHolder());

			mediaplayer.setDataSource(videoPath);
			mediaplayer.prepareAsync();

			bt_play.setEnabled(false);
			bt_pause.setEnabled(true);
			bt_stop.setEnabled(true);
			sb_progress.setEnabled(true);

			mediaplayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO 自动生成的方法存根
					mediaplayer.start();
					final int max = mediaplayer.getDuration();

					sb_progress.setMax(max);
					mediaplayer.seekTo(currentPosition2);
					new Thread() {
						public void run() {
							flag = true;
							while (flag) {
								int progress = mediaplayer.getCurrentPosition();
								sb_progress.setProgress(progress);
								Message message = new Message();
								
								Bundle bundle=new Bundle();
								message.setData(bundle);
								bundle.putInt("1", max);
								bundle.putInt("2", progress);
								message.what = 0;
								handler.sendMessage(message);
								
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
							}
						}

						
					}.start();
				}
			});
			mediaplayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO 自动生成的方法存根
					bt_play.setEnabled(true);
				}
			});
			mediaplayer.setOnErrorListener(new OnErrorListener() {

				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO 自动生成的方法存根
					bt_play.setEnabled(true);
					flag = false;
					return false;

				}
			});
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			Toast.makeText(this, "播放失败！", 1).show();
		}

	}

}

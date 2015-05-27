package com.zhy.clippic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Currency;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zhy.view.ClipImageLayout;
/**
 * http://blog.csdn.net/lmj623565791/article/details/39761281
 * 
 *
 */
public class MainActivity extends Activity
{
	private ClipImageLayout mClipImageLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
		mClipImageLayout.getmZoomImageView().setImageResource(R.drawable.tbug);
	}

	public static File createCacheDir(String dirPath) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public static File photoCacheDir = createCacheDir(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "clilp/");
	public static String cacheFileName = "myphototemp.jpg";
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.id_action_clip:
			try {
				Bitmap bitmap = mClipImageLayout.clip();
				File file = new File(photoCacheDir, System.currentTimeMillis()+cacheFileName);
				if(!file.exists()){
					file.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
				if (bitmap != null) {
					if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
						fos.close();
						fos.flush();
					}
					if(!bitmap.isRecycled()){
						bitmap.isRecycled();
					}
					Toast.makeText(this, "保存成功", 0).show();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
//			Intent intent = new Intent(this, ShowImageActivity.class);
//			intent.putExtra("bitmap", datas);
//			startActivity(intent);

			break;
		}
		return super.onOptionsItemSelected(item);
	}
}

package com.example.qqjiemian;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
public class MainActivity extends Activity implements OnClickListener {
	
	private final static int REQUEST_CONNECT_DEVICE = 1; // 宏定义查询设备句柄
	private final static String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB"; // SPP服务UUID号

	public EditText juli_shuru;
	public EditText wendu_shuru;
	public EditText shidu_shuru;	
	public Boolean bluetoothStatus=false;
	private InputStream is; // 输入流，用来接收蓝牙数据
	BluetoothDevice _device = null; // 蓝牙设备
	BluetoothSocket _socket = null; // 蓝牙通信socket
	boolean bRun = true;
	@SuppressLint("NewApi")
	
	private BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter(); // 获取本地蓝牙适配器，即蓝牙设备

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        juli_shuru=(EditText)findViewById(R.id.juli_shuru);
        wendu_shuru=(EditText)findViewById(R.id.wendu_shuru);
        shidu_shuru=(EditText)findViewById(R.id.shidu_shuru);
        Button btnCall=(Button)findViewById(R.id.btn_call);
        Button btnED=(Button)findViewById(R.id.wd_laba);
        Button btnJL=(Button)findViewById(R.id.jl_laba);
        Button btnSD=(Button)findViewById(R.id.sd_laba);
        Button lianjieBtn=(Button)findViewById(R.id.lianjie_Button);
        btnCall.setOnClickListener(this);
        btnJL.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				System.out.println("距离");
				 try {
						OutputStream os =  _socket.getOutputStream(); // 蓝牙连接输出流
							os.write(0x01);
							
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e1) {
//							e1.printStackTrace();
//						}
						is=_socket.getInputStream();
//					    int aa=is.read()-48;
						 int b=0;
						    for(int a=0;a<1;a++)
						    {
						    	for(int a1=0;a1<1;a1++)
						    	{
//						    		for(int a2=0;a2<1;a2++)
//						    		{
//					    	           b=(is.read())*100;
//						    		}
						    		b=(is.read()-48)*10;
						    	}
						    	b=b+(is.read()-48);
						    }
					    
					    juli_shuru.setText(""+b);
						Toast.makeText(getApplicationContext(), " 发送成功.. ", Toast.LENGTH_LONG).show();
					} catch (IOException e) {
					}
				
			}
		});
        
  /*
   * 温度button按钮响应      
   */
     btnED.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				System.out.println("温度");
				 try {
						OutputStream os =  _socket.getOutputStream(); // 蓝牙连接输出流
							os.write(0x02);
							
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e1) {
//							e1.printStackTrace();
//						}
						is=_socket.getInputStream();
						/*		    int c=0;
    				    int d1=16;
    				    int d2=8;
    				    int d3=4;
    				    int d4=2;
    				    for(int i=0;i<1;i++){
						    for(int i1=0;i1<1;i1++){
						    	 for(int n=0;n<1;n++){
						    		 for(int m=0;m<1;m++){
						    			 for(int f=0;f<1;f++){
						    				 c=c+(is.read())*d1;
								    	 }
						    			 c=c+(is.read())*d2;
							    	 }
						    		 c=c+(is.read())*d3;
						    	 }
						    	c=c+(is.read())*d4;
						    }
						    c=c+(is.read());
    				    } */ 
						// int a=is.read()-48;
						//Integer.valueOf("FFFF",16).toString() ;
						    int c=0;
						    for(int n=0;n<1;n++){
						    	for(int n1=0;n1<1;n1++){
						    	c=(is.read()-48)*10;
						    	}
						    	c=c+(is.read()-48);
						    }
						   
					    wendu_shuru.setText(""+c);
						Toast.makeText(getApplicationContext(), " 发送成功.. ", Toast.LENGTH_LONG).show();
				 }catch (IOException e) {
					}
				
			}
		});
     /*
      * 湿度button按钮响应      
      */
      btnSD.setOnClickListener(new OnClickListener() {
   			
   			@SuppressLint("NewApi")
   			@Override
   			public void onClick(View arg0) {
   				System.out.println("湿度");
   				 try {
   						OutputStream os =  _socket.getOutputStream(); // 蓝牙连接输出流
   							os.write(0x03);
   							
//   						try {
//   							Thread.sleep(1000);
//   						} catch (InterruptedException e1) {
//   							e1.printStackTrace();
//   						}
   						is=_socket.getInputStream();
   						int d=0;
					    for(int m=0;m<1;m++){
					    	for(int m1=0;m1<1;m1++){
					    	d=(is.read()-48)*10;
					    	}
					    	d=d+(is.read()-48);
					    }
   					    
   					 shidu_shuru.setText(""+d);
   						Toast.makeText(getApplicationContext(), " 发送成功.. ", Toast.LENGTH_LONG).show();
   					} catch (IOException e) {
   					}
   				
   			}
   		});
        lianjieBtn.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {// 连接按键响应函数
				//判断蓝牙是否连接，如果连接直接跳转，否则连接成功后再跳转
				if(bluetoothStatus){
					Toast.makeText(getApplicationContext(), " 蓝牙链接成功..", Toast.LENGTH_LONG).show();
				}
				else{
					if (_bluetooth.isEnabled() == false) { // 如果蓝牙服务不可用则提示
						Toast.makeText(getApplicationContext(), " 打开蓝牙中...", Toast.LENGTH_LONG).show();
						return;
					}

					// 如未连接设备则打开DeviceListActivity进行设备搜索
				 
					if (_socket == null) {
						Intent serverIntent = new Intent(getApplicationContext(),DeviceListActivity.class); // 跳转程序设置
						startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE); // 设置返回宏定义
						 
					} else {
						
						// 关闭连接socket
						try {
							//蓝牙断开控制灯
							OutputStream os = _socket.getOutputStream(); // 蓝牙连接输出流
	
						} catch (IOException e) {
						}
						try {
							is.close();
							_socket.close();
							_socket = null;
							bRun = false;
						} catch (IOException e) {
						}
					}
				}
			}
		});
        
     // 如果打开本地蓝牙设备不成功，提示信息，结束程序
     		if (_bluetooth == null) {
     			Toast.makeText(this, "无法打开手机蓝牙，请确认手机是否有蓝牙功能！", Toast.LENGTH_LONG)
     					.show();
     			finish();
     			return;
     		}
     		
     	// 设置设备可以被搜索
    		new Thread() {
    			@SuppressLint("NewApi")
    			public void run() {
    				if (_bluetooth.isEnabled() == false) {
    					_bluetooth.enable();
    				}
    			}
    		}.start();
    }
 //       class MyOnClickListener implements OnClickListener {
//
  //      	@Override
    //    	public void onClick(View v){
      //  		System.out.println("MainActivity2 拨打电话");
        //		call();
        //	}
       // }
  //  }

    public void call(){
    	System.out.println("拨打电话");
    	EditText etNumber=(EditText)findViewById(R.id.et_number);
    	String number=etNumber.getText().toString();
    	Intent intent=new Intent();
    	intent.setAction(Intent.ACTION_CALL);
    	intent.setData(Uri.parse("tel:"+number));
    	startActivity(intent);
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

 // 接收活动结果，响应startActivityForResult()
 		@TargetApi(Build.VERSION_CODES.ECLAIR)
 		@SuppressLint("NewApi")
 		public void onActivityResult(int requestCode, int resultCode, Intent data) {
 			switch (requestCode) {
 			case REQUEST_CONNECT_DEVICE: // 连接结果，由DeviceListActivity设置返回
 				// 响应返回结果
 				if (resultCode == Activity.RESULT_OK) { // 连接成功，由DeviceListActivity设置返回
 					bluetoothStatus=!bluetoothStatus;
 					// MAC地址，由DeviceListActivity设置返回
 					String address = data.getExtras().getString(
 							DeviceListActivity.EXTRA_DEVICE_ADDRESS);
 					// 得到蓝牙设备句柄
 					_device = _bluetooth.getRemoteDevice(address);
 				
 				//   ConstantClass.EXTRA_DEVICE_ADDRESS=address;
 					// 用服务号得到socket
 					try {
 						_socket = _device.createRfcommSocketToServiceRecord(UUID
 								.fromString(MY_UUID));
 						
 					} catch (IOException e) {
 						Toast.makeText(this, "连接失败！", Toast.LENGTH_SHORT).show();
 					}
 				
 					// 连接socket
 					try {
 						_socket.connect();
 						Toast.makeText(this, "连接" + _device.getName() + "成功！",Toast.LENGTH_SHORT).show();
 						
 						//蓝牙链接成功
 					
 					 
 					} catch (IOException e) {
 						try {
 							Toast.makeText(this, "连接失败！", Toast.LENGTH_SHORT)
 									.show();
 							_socket.close();
 							_socket = null;
 						} catch (IOException ee) {
 							Toast.makeText(this, "连接失败！", Toast.LENGTH_SHORT)
 									.show();
 						}

 						return;
 					}
 				}
 				break;
 			default:
 				break;
 			}
 		}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("MainActivity 拨打电话");
      	call();
	}
}
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
	
	private final static int REQUEST_CONNECT_DEVICE = 1; // �궨���ѯ�豸���
	private final static String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB"; // SPP����UUID��

	public EditText juli_shuru;
	public EditText wendu_shuru;
	public EditText shidu_shuru;	
	public Boolean bluetoothStatus=false;
	private InputStream is; // ������������������������
	BluetoothDevice _device = null; // �����豸
	BluetoothSocket _socket = null; // ����ͨ��socket
	boolean bRun = true;
	@SuppressLint("NewApi")
	
	private BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter(); // ��ȡ�����������������������豸

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
				System.out.println("����");
				 try {
						OutputStream os =  _socket.getOutputStream(); // �������������
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
						Toast.makeText(getApplicationContext(), " ���ͳɹ�.. ", Toast.LENGTH_LONG).show();
					} catch (IOException e) {
					}
				
			}
		});
        
  /*
   * �¶�button��ť��Ӧ      
   */
     btnED.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				System.out.println("�¶�");
				 try {
						OutputStream os =  _socket.getOutputStream(); // �������������
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
						Toast.makeText(getApplicationContext(), " ���ͳɹ�.. ", Toast.LENGTH_LONG).show();
				 }catch (IOException e) {
					}
				
			}
		});
     /*
      * ʪ��button��ť��Ӧ      
      */
      btnSD.setOnClickListener(new OnClickListener() {
   			
   			@SuppressLint("NewApi")
   			@Override
   			public void onClick(View arg0) {
   				System.out.println("ʪ��");
   				 try {
   						OutputStream os =  _socket.getOutputStream(); // �������������
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
   						Toast.makeText(getApplicationContext(), " ���ͳɹ�.. ", Toast.LENGTH_LONG).show();
   					} catch (IOException e) {
   					}
   				
   			}
   		});
        lianjieBtn.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {// ���Ӱ�����Ӧ����
				//�ж������Ƿ����ӣ��������ֱ����ת���������ӳɹ�������ת
				if(bluetoothStatus){
					Toast.makeText(getApplicationContext(), " �������ӳɹ�..", Toast.LENGTH_LONG).show();
				}
				else{
					if (_bluetooth.isEnabled() == false) { // ����������񲻿�������ʾ
						Toast.makeText(getApplicationContext(), " ��������...", Toast.LENGTH_LONG).show();
						return;
					}

					// ��δ�����豸���DeviceListActivity�����豸����
				 
					if (_socket == null) {
						Intent serverIntent = new Intent(getApplicationContext(),DeviceListActivity.class); // ��ת��������
						startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE); // ���÷��غ궨��
						 
					} else {
						
						// �ر�����socket
						try {
							//�����Ͽ����Ƶ�
							OutputStream os = _socket.getOutputStream(); // �������������
	
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
        
     // ����򿪱��������豸���ɹ�����ʾ��Ϣ����������
     		if (_bluetooth == null) {
     			Toast.makeText(this, "�޷����ֻ���������ȷ���ֻ��Ƿ����������ܣ�", Toast.LENGTH_LONG)
     					.show();
     			finish();
     			return;
     		}
     		
     	// �����豸���Ա�����
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
      //  		System.out.println("MainActivity2 ����绰");
        //		call();
        //	}
       // }
  //  }

    public void call(){
    	System.out.println("����绰");
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

 // ���ջ�������ӦstartActivityForResult()
 		@TargetApi(Build.VERSION_CODES.ECLAIR)
 		@SuppressLint("NewApi")
 		public void onActivityResult(int requestCode, int resultCode, Intent data) {
 			switch (requestCode) {
 			case REQUEST_CONNECT_DEVICE: // ���ӽ������DeviceListActivity���÷���
 				// ��Ӧ���ؽ��
 				if (resultCode == Activity.RESULT_OK) { // ���ӳɹ�����DeviceListActivity���÷���
 					bluetoothStatus=!bluetoothStatus;
 					// MAC��ַ����DeviceListActivity���÷���
 					String address = data.getExtras().getString(
 							DeviceListActivity.EXTRA_DEVICE_ADDRESS);
 					// �õ������豸���
 					_device = _bluetooth.getRemoteDevice(address);
 				
 				//   ConstantClass.EXTRA_DEVICE_ADDRESS=address;
 					// �÷���ŵõ�socket
 					try {
 						_socket = _device.createRfcommSocketToServiceRecord(UUID
 								.fromString(MY_UUID));
 						
 					} catch (IOException e) {
 						Toast.makeText(this, "����ʧ�ܣ�", Toast.LENGTH_SHORT).show();
 					}
 				
 					// ����socket
 					try {
 						_socket.connect();
 						Toast.makeText(this, "����" + _device.getName() + "�ɹ���",Toast.LENGTH_SHORT).show();
 						
 						//�������ӳɹ�
 					
 					 
 					} catch (IOException e) {
 						try {
 							Toast.makeText(this, "����ʧ�ܣ�", Toast.LENGTH_SHORT)
 									.show();
 							_socket.close();
 							_socket = null;
 						} catch (IOException ee) {
 							Toast.makeText(this, "����ʧ�ܣ�", Toast.LENGTH_SHORT)
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
		System.out.println("MainActivity ����绰");
      	call();
	}
}
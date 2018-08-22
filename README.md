# DeviceLibrary
一个获取手机设备信息的库，适配国产ROM存在的各种问题。


目前适配的ROM问题：

imei问题：

1.部分华为手机(android 5.0)的imei存在问题，实际获取到的是meid
  
  解决办法：通过Hook 获取到华为真实的imei。

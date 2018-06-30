# AutoPlayVideo
用mvp模式,RecyclerView编写一个仿新浪微博滑动自动播放网络视频,使用了JC播放器,但是修改了ijkplayer播放器库支持播放https链接！
当只需要播放http视频时，在build.gradle下打开implementation 'tv.danmaku.ijk.media:ijkplayer-armv7a:0.8.8',屏蔽
//    sourceSets {
 //        main {
  //            jniLibs.srcDirs = ['libs']
//        }
//    }
当需要支持播放https视频时，关闭implementation 'tv.danmaku.ijk.media:ijkplayer-armv7a:0.8.8'，打开
//    sourceSets {
 //        main {
  //            jniLibs.srcDirs = ['libs']
//        }
//    }
目前https视频会有一个bug，，就是当视频列表界面托动播放进度条，当还在loading加载的时候点击全屏按钮,
全屏界面会出现进度条显示为0并且loading状态不消失，但是视屏仍然在播放

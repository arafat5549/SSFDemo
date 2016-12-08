package foo.lesson.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * 相当于IOC容器，注册了各种各样的实现类
 * 
 * 需求1：你有一个FontFactory类 可以获取FontProvider提供者
 * 提供者多种多样字库提供类：3个实现类 （ Local本地系统 Disk磁盘 Net网络）
 * 继承同一个 interface IFontProvider{  getFont() ...}
 * 
 * 需求2：每一个实现类都得加个缓存，每一个实现类都能写一个缓存系统 ，
 * 最简单的做法 每个类都写一遍
 * 一遍我们会实现一个缓存类 由缓存类来添加我们的功能，这种我们就叫静态代理
 * 
 * 需求3：你要实现ImageFactory
 * 提供者多种多样图片提供类：3个实现类 （ Local本地系统 Disk磁盘 Net网络）
 * 
 * @author wyy
 * 2016年11月30日
 *
 */
public abstract class ProviderFactory {

}
//缓存类
class Cache{
	private Map<String, Object> cacheMap = new HashMap<String, Object>();
	
	public Object getFromCache(String key){
		if(cacheMap.get(key)!=null){
			return cacheMap.get(key);
		}
		return null;
	}
	
	public void addToCache(String key,Object f){
		cacheMap.put(key, f);
	}
}

//字体类
class Font{
	private String fontName;
	public Font(String fontName){this.fontName=fontName;}
	@Override
	public String toString() {
		return "Font [fontName=" + fontName + "]";
	}
	
}
//图片类
class Image{
	private String fontName;
	public Image(String fontName){this.fontName=fontName;}
	@Override
	public String toString() {
		return "Image [fontName=" + fontName + "]";
	}
	
}
//
interface IFontProvider{
	Font getFont(String key);
}

class LocalFontProvider implements IFontProvider{
	
	private Cache cache;
	public void SetCache(Cache cache){
		this.cache = cache;
	}
	
	@Override
	public Font getFont(String key) 
	{
		//.缓存功能
		return null;
	}
	
}
//
class DiskFontProvider implements IFontProvider
{
	private Cache cache;
	public void SetCache(Cache cache){
		this.cache = cache;
	}
	@Override
	public Font getFont(String key) {
		//.缓存功能
		return null;
	}
	
}
//
class NetFontProvider implements IFontProvider{
	private Cache cache;
	public void SetCache(Cache cache){
		this.cache = cache;
	}
	@Override
	public Font getFont(String key) {
//		//.缓存功能
//		Font f = (Font)cache.getFromCache(key);
//		if(f==null){
//			Font newFont = new Font(key);
//			cache.addToCache(key,newFont);
//			return newFont;
//		}
//		return f;
		return new Font(key);
	}
}

//
interface IImageProvider{
	Image getImage(String key);
}

class NetImageProvider implements IImageProvider{
	private Cache cache;
	public void SetCache(Cache cache){
		this.cache = cache;
	}
	@Override
	public Image getImage(String key) {
//		//.缓存功能
//		Image f = (Image)cache.getFromCache(key);
//		if(f==null){
//			Image newFont = new Image(key);
//			cache.addToCache(key,newFont);
//			return newFont;
//		}
//		return f;
		return  new Image(key);
	}
}
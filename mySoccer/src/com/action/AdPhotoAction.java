package com.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.domain.AdPhoto;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.utils.UUIDUtils;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class AdPhotoAction extends ActionSupport {
	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	
	private File adPicture; // ͼƬ
	private String adPictureFileName;//����
	private String adLink;//����
	public IAdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	
	
	public File getAdPicture() {
		return adPicture;
	}
	public void setAdPicture(File adPicture) {
		this.adPicture = adPicture;
	}
	public String getAdPictureFileName() {
		return adPictureFileName;
	}
	public void setAdPictureFileName(String adPictureFileName) {
		this.adPictureFileName = adPictureFileName;
	}
	
	
	public String getAdLink() {
		return adLink;
	}
	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}
	
	//����ֲ�ͼ
	@Action(value="createNewAdPhoto",results={@Result(name="success",location="findAllAdPhoto",type="redirectAction")})
	public String createNewAdPhoto() {
		AdPhoto adPhoto = new AdPhoto();
		if(adPictureFileName!=null) {
			adPictureFileName = UUIDUtils.getUUID()+adPictureFileName;
			//�����ϴ��ļ�
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/adPicture"),adPictureFileName);
			try {
				FileUtils.copyFile(adPicture, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String adPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/adPicture/"+adPictureFileName;
			adPhoto.setAdPicture(adPhotosrc);
			
		}
		adPhoto.setAdBackLink(adLink);
		adLink = adLink+"&pageFlag=1";
		adPhoto.setAdLink(adLink);
		adminService.addAdPhoto(adPhoto);
		return SUCCESS;
	}
	
	//Ѱ���ֲ�ͼ
	@Action(value="findAllAdPhoto",results={@Result(name="success",location="/jsp/admin/findAdPhoto.jsp")})
	public String findAllAdPhoto() {
		List<AdPhoto> adPhotoList = adminService.findAllAdPhoto();
		ServletActionContext.getRequest().setAttribute("adPhotoList", adPhotoList);
		return SUCCESS;
	}
	
	//ɾ���ֲ�ͼ
	@Action(value="deleteAdPhotoById",results={@Result(name="success",location="findAllAdPhoto",type="redirectAction")})
	public String deleteAdPhotoById() {
		String adId = ServletActionContext.getRequest().getParameter("adId");
		adminService.deleteAdPhotoById(adId);
		return SUCCESS;
	}
	
	//�޸��ֲ�ͼ����ȡԭ��������
	@Action(value="jumpToUpdateAdPhoto")
	public void jumpToUpdateAdPhoto() {
		String adId = ServletActionContext.getRequest().getParameter("adId");
		//ServletActionContext.getRequest().setAttribute("updateAdId", adId);
		AdPhoto adPhoto = adminService.findAdPhotoById(adId);
		String json = JSONArray.toJSONString(adPhoto.getAdLink());
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//�޸��ֲ�ͼ����
	@Action(value="updateAdPhoto",results={@Result(name="success",location="findAllAdPhoto",type="redirectAction")})
	public String updateAdPhoto() {
		String adId = ServletActionContext.getRequest().getParameter("adId");
		AdPhoto adPhoto = adminService.findAdPhotoById(adId);
		if(adPictureFileName!=null) {
			adPictureFileName = UUIDUtils.getUUID()+adPictureFileName;
			//�����ϴ��ļ�
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/adPicture"),adPictureFileName);
			try {
				FileUtils.copyFile(adPicture, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String adPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/adPicture/"+adPictureFileName;
			adPhoto.setAdPicture(adPhotosrc);
			
		}
		adPhoto.setAdBackLink(adLink);
		adLink = adLink+"&pageFlag=1";
		adPhoto.setAdLink(adLink);
		
		adminService.updateAdPhoto(adPhoto);
		return SUCCESS;
	}
}



















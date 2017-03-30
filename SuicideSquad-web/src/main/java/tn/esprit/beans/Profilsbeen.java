package tn.esprit.beans;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;

@ManagedBean
@ApplicationScoped
public class Profilsbeen {
private byte[] defaultpicture;

public Profilsbeen(){
	
}
@PostConstruct
public void init(){
	InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/avatars/avatar_01.jpg");
	try {
		defaultpicture=IOUtils.toByteArray(is);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public byte[] getDefaultpicture() {
	return defaultpicture;
}

public void setDefaultpicture(byte[] defaultpicture) {
	this.defaultpicture = defaultpicture;
}
}

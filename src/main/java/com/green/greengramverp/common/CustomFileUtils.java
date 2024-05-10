package com.green.greengramverp.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
@Component //빈등록
public class CustomFileUtils {
    private final String parentPath;
    //폴더 만들고, 랜덤이름 만들기

    public CustomFileUtils(@Value("${information.greengram}")String parentPath){
        this.parentPath=parentPath;//직접 초기화 하지 않고 이런 방법을 쓰면 서버를 재가동 하지 않아도 됨
    }
    public String getExt(String fileName){ //확장자 추출
        int extIdx=fileName.lastIndexOf('.');
        String ext=fileName.substring(extIdx);
        return ext;
    }
    public String makeRandomFileName(){return UUID.randomUUID().toString();}//3. 랜덤 이름 생성
    public String makeRandomFileName(String fileName){return makeRandomFileName()+getExt(fileName);}//2. 랜덤 이름을 만들고 확장자를 붙인다
    public String makeRandomFileName(MultipartFile mf){
        return mf==null || mf.isEmpty() ? null : makeRandomFileName(mf.getOriginalFilename());
    }//1.파일의 이름을 받아온다

    public void transferTo(MultipartFile mf, String target) throws Exception{//에러를 꼭 감싸주어야 함
        File shoot=new File(parentPath, target); // 파일에 경로를 저장
        mf.transferTo(shoot);//파일을 변경하고 경로로 보냄
    }

    public void makeFolder(String path){
        File folder=new File(parentPath, path);
        folder.mkdirs();
    }


}

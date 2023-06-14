package com.petbayo.petbayo.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petbayo.petbayo.Dao.UserDao;
import com.petbayo.petbayo.Model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    @Override
    public boolean register(User item) {
        return dao.register(item);
    }

    @Override
    public boolean login(User user) {
        User item = dao.login(user);

        if(item != null) {
            user.setUserId(item.getUserId());
            user.setEmail(item.getEmail());
            user.setPwd(item.getPwd());
            user.setNickname(item.getNickname());
            user.setBirth(item.getBirth());
            user.setGender(item.getGender());
            user.setUserImage(item.getUserImage());
            user.setImagePath(item.getImagePath());
            user.setCarePoint(item.getCarePoint());
            user.setUserIntro(item.getUserIntro());

            return true;
        } else
            return false;
    }



    @Override
    public boolean checkEmail(String email) {
        String count = Integer.toString(dao.checkEmail(email));
        if(count.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkNickname(String nickname) {
        String count = Integer.toString(dao.checkNickname(nickname));
        if(count.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findOne(int userId) {
        return dao.findOne(userId);
    }

    @Override
    public void uploadProfile(User user, MultipartFile file) throws IOException {
        String uploadPath = "C:/img/userProfiles";
        File directory = new File(uploadPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        int index = file.getOriginalFilename().indexOf(".");
        String extend = file.getOriginalFilename().substring(index);

        String fileName = UUID.randomUUID().toString().replaceAll("-", "").substring(0,12);
        String fullname = fileName + extend;
        String nickname = String.valueOf(user.getNickname());

        File uploadDir = new File(uploadPath, nickname);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File saveFile = new File(uploadDir.getPath(), fullname);

        file.transferTo(saveFile);

        user.setUserImage(fullname);

        String localPath = "C:\\img\\userProfiles\\" + user.getNickname() + "\\";
        user.setImagePath(localPath + fullname);

        dao.updateProfile(user);

        if (user.getUserImage() != null) {
            deleteOldFiles(uploadDir.getPath());
        }

    }
    private void deleteOldFiles(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null && files.length > 1) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

            for (int i = 1; i < files.length; i++) {
                files[i].delete();
            }
        }
    }


}

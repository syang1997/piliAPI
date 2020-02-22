package com.pili.syang.controller;

import com.coremedia.iso.IsoFile;
import com.pili.syang.BaseInfo;
import com.pili.syang.entity.User;
import com.pili.syang.entity.Video;
import com.pili.syang.enums.StatusCode;
import com.pili.syang.service.UserService;
import com.pili.syang.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoController extends BaseController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private UserService userService;

    @PostMapping("/contribute")
    public BaseInfo uploadVideo(@RequestBody Video video) {
        if (video.getAuthor() == null) {
            return BaseInfo.BackInfo(StatusCode.NEED_LOGIN, null);
        } else {
            video.setCollect(0);
            video.setHits(0);
            video.setCreatetime(System.currentTimeMillis());
            videoService.upload(video);
        }
        return BaseInfo.BackInfo(StatusCode.SUCCESS, null);
    }

    @PostMapping("/cover")
    public BaseInfo importData(MultipartFile file, Integer uid) throws IOException {
        String realPath = "D:\\test\\cover\\";
        if (uid == null) {
            return BaseInfo.BackInfo(StatusCode.NEED_LOGIN, null);
        }
        User one = userService.findOne(uid);
        if (one == null) {
            return BaseInfo.BackInfo(StatusCode.UNKNOWN_ERROR, null);
        }
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder, newName));
        String url = "http://localhost:8181/upload/cover/" + newName;
        return BaseInfo.BackInfo(StatusCode.SUCCESS, url);
    }

    @PostMapping("/upload")
    public BaseInfo importVideo(MultipartFile file, Integer uid) throws IOException {
        String realPath = "D:\\test\\video\\";
        if (uid == null) {
            return BaseInfo.BackInfo(StatusCode.NEED_LOGIN, null);
        }
        User one = userService.findOne(uid);
        if (one == null) {
            return BaseInfo.BackInfo(StatusCode.UNKNOWN_ERROR, null);
        }
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder, newName));
        String url = "http://localhost:8181/upload/video/" + newName;
        IsoFile isoFile = new IsoFile("D:\\test\\video\\" + newName);
        long lengthInSeconds =
                isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                        isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
        int h = 0;
        int m = 0;
        int s = 0;
        String duration = "";
        s = (int) (lengthInSeconds % 60);
        m = (int) ((lengthInSeconds / 60) % 60);
        h = (int) (lengthInSeconds / 3600);
        if (h != 0) {
            duration += h + ":";
        }
        duration += m + ":";
        duration += s;
        Map videoData = new HashMap();
        videoData.put("vurl", url);
        videoData.put("duration", duration);
        return BaseInfo.BackInfo(StatusCode.SUCCESS, videoData);
    }


    @PostMapping("/hot")
    public BaseInfo hotClassify() {
        HashMap<String, List<Video>> hots = videoService.hotVideos();
        return BaseInfo.BackInfo(StatusCode.SUCCESS, hots);
    }

    @PostMapping("/tops")
    public BaseInfo indexTops( Integer num) {
        HashMap<String, List<Video>> hots = videoService.topVideos(num);
        return BaseInfo.BackInfo(StatusCode.SUCCESS, hots);
    }

    @PostMapping("/top")
    public BaseInfo classifyTops( String classify,  long time, Integer num) {
        long daytime = 1000 * 3600 * 24*time;
        List<Video> videos = videoService.classifyTop(classify, num, daytime);
        return BaseInfo.BackInfo(StatusCode.SUCCESS, videos);
    }

    @PostMapping("/play")
    public BaseInfo getVideo(@RequestBody Video video) {
        if (video.getVid() == null) {
            return BaseInfo.BackInfo(StatusCode.UNKNOWN_ERROR, null);
        }
        Video video1 = videoService.getVideo(video.getVid());
        return BaseInfo.BackInfo(StatusCode.SUCCESS, video1);
    }
}

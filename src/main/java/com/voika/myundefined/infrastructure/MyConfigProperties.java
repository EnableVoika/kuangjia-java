package com.voika.myundefined.infrastructure;

import com.voika.myundefined.infrastructure.utils.StringUtil;
import com.voika.myundefined.infrastructure.utils.UrlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@EnableConfigurationProperties({MyConfigProperties.class})
@ConfigurationProperties(prefix = "my-config")
@Configuration("myConfigProperties")
public class MyConfigProperties {

    public File getFile() {
        return file;
    }


    public Jwt getJwt() {
        return jwt;
    }

    MyConfigProperties() {
        this.file = new File();
        this.jwt = new Jwt();
    }

    private File file;
    private Jwt jwt;

    public class File {

        public String getUploadDir() {
//            if (StringUtil.isNotEmpty(uploadDir) && (0 == uploadDir.indexOf("classpath:"))) {
//                String path = "";
//                if ("".endsWith(uploadDir.replace("classpath:", ""))) {
//                    path = this.getClass().getClassLoader().getResource("").getPath();
//                    return path;
//                }
//                String classpath = this.getClass().getClassLoader().getResource("").getPath();
//                if (!classpath.endsWith("/")) {
//                    classpath = classpath + "/";
//                }
//                String var = uploadDir.replace("classpath:", "");
//                path = classpath + var;
//                if (var.startsWith("/")) {
//                    path = classpath + var.replaceFirst("/", "");
//                }
//                return path;
//            }
            return UrlUtil.resolveClassPath(uploadDir);
        }

        public void setUploadDir(String uploadDir) {
            this.uploadDir = uploadDir;
        }

        private String uploadDir;
    }

    public class Jwt {

        public String getSecrityKey() {
            return secrityKey;
        }

        public void setSecrityKey(String secrityKey) {
            this.secrityKey = secrityKey;
        }

        public long getDefaultExpirTime() {
            return defaultExpirTime;
        }

        public void setDefaultExpirTime(long defaultExpirTime) {
            this.defaultExpirTime = defaultExpirTime;
        }

        private String secrityKey;
        private long defaultExpirTime;
    }

}
//@Data
//class File {
//    private String uploadDir;
//}



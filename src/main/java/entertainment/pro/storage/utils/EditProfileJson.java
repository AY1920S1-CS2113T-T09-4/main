package entertainment.pro.storage.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entertainment.pro.model.UserProfile;

import java.io.*;

/**
 * class that deals with editing the UserProfile.json file
 */
public class EditProfileJson {
    private ObjectMapper mapper = new ObjectMapper();
    private File file;
    private InputStream inputStream;
    private TypeReference<UserProfile> typeReference = new TypeReference<UserProfile>() {
    };


    public EditProfileJson() throws FileNotFoundException {
        file = new File("EPdata/userProfile.json");
        this.inputStream = new FileInputStream(file);
    }

    public UserProfile load() throws IOException {
        return mapper.readValue(inputStream, typeReference);
    }

    /**
     * update json file with any changes made to user profile
     */
    public void updateProfile(UserProfile userProfile) throws IOException {
        File oldFile = file;
        File newFile = new File("EPdata/tempUserProfile.json");
        mapper.writeValue(newFile, userProfile);
        inputStream.close();
        oldFile.delete();
        newFile.renameTo(new File(file.getAbsolutePath()));
    }
}
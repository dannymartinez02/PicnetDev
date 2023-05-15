package Services;

import Model.Image;

public interface ImageService {
    Image save(Image image);
    Image findById(Long id);
    void deleteById(Long id);
}

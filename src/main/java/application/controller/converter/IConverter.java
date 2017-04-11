package application.controller.converter;

import java.util.List;

public interface IConverter<T, G> {

    T convert(G input);
    List<T> convert(List<G> input);

}

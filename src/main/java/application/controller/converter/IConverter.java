package application.controller.converter;

public interface IConverter<T, G> {

    T convert(G input);

}

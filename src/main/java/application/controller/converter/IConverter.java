package application.controller.converter;

public interface IConverter<View, Entity> {

    View convert(Entity entity);

}

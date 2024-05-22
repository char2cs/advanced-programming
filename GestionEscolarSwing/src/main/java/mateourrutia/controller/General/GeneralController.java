package mateourrutia.controller.General;

import mateourrutia.view.General.GeneralView;
import java.util.List;

public class GeneralController<T> {
    private final GeneralTableModel<T> model;
    private final GeneralView<T> view;

    public GeneralController(
            List<T> objects,
            Class<T> clazz
    ) {
        this.model = new GeneralTableModel<T>( objects, clazz );
        this.view = new GeneralView<T>( this.model );
    }

    public void addRow(T obj) {
        model.addRow(obj);
    }

    public void removeRow(int rowIndex) {
        model.removeRow(rowIndex);
    }

    public void updateRow(int rowIndex, T obj) {
        model.updateRow(rowIndex, obj);
    }

    public GeneralTableModel<T> getModel() {
        return model;
    }

    public GeneralView<T> getView() {
        return view;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import com.xtesoft.xtecuannet.framework.viewcontroller.beans.XBaseBean;
import com.xtesoft.xtecuannet.framework.viewcontroller.utils.ViewUtils;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import modelo.entidades.CatDepartamentos;
import modelo.entidades.CatMunicipios;
import modelo.entidades.CatMunicipiosPK;
import modelo.servicios.CatDepartamentosService;
import modelo.servicios.CatMunicipiosService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lcordero
 */
public class CatMunicipiosBean extends XBaseBean {

    @Autowired
    private CatMunicipiosService servMunis;
    @Autowired
    private CatDepartamentosService servDeptos;
    private CatMunicipios current;
    private CatMunicipios search;
    private CatMunicipios selected;
    private List<SelectItem> itemsDepto;
    private Boolean insert = Boolean.TRUE;
    private LazyDataModel<CatMunicipios> gridList;

    @PostConstruct
    private void __init() {
        this.itemsDepto = ViewUtils.fromListToSelectItem(servDeptos.findAll(),
                "nomdepartamento", "coddepto");

        this.fillGridList();
    }

    private void fillGridList() {
        this.gridList = new LazyDataModel<CatMunicipios>() {

            @Override
            public Object getRowKey(CatMunicipios object) {
                return object.getCatMunicipiosPK();
            }

            @Override
            public List<CatMunicipios> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                this.setRowCount(servMunis.count());
                return servMunis.findSomeRange(first, pageSize);
            }

            @Override
            public CatMunicipios getRowData(String rowKey) {
                getLogger().info("rowKey: " + rowKey);
                return super.getRowData(rowKey);
            }

            @Override
            public void setRowIndex(int rowIndex) {
                /*
                 * The following is in ancestor (LazyDataModel): this.rowIndex =
                 * rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
                 */
                if (rowIndex == -1 || getPageSize() == 0) {
                    super.setRowIndex(-1);
                } else {
                    super.setRowIndex(rowIndex % getPageSize());
                }
            }
        };
    }

    public void save(ActionEvent event) {

        try {


            current.getCatDepartamentos().setCoddepto(current.getCatMunicipiosPK().getCoddepartamento());

            servMunis.create(current);

            fillGridList();

            current = new CatMunicipios(new CatMunicipiosPK());
            current.setCatDepartamentos(new CatDepartamentos());

            String msg = getMessage("CatMunicipios_form_msg_success1", new Object[]{current.getCatMunicipiosPK().getCodmunicipio(),
                        current.getCatMunicipiosPK().getCoddepartamento()}, Locale.ENGLISH);

            String resume = getMessage("CatMunicipios_form_msg_resume", null, Locale.ENGLISH);
            current = new CatMunicipios(new CatMunicipiosPK());
            current.setCatDepartamentos(new CatDepartamentos());
            addMessage(resume, msg);


        } catch (Exception e) {
            getLogger().error("Error al guardar el municipio: ", e);
            
        }

    }

    public void update(ActionEvent event) {
    }

    public void find(ActionEvent event) {
    }

    public CatDepartamentosService getServDeptos() {
        return servDeptos;
    }

    public void setServDeptos(CatDepartamentosService servDeptos) {
        this.servDeptos = servDeptos;
    }

    public CatMunicipiosService getServMunis() {
        return servMunis;
    }

    public void setServMunis(CatMunicipiosService servMunis) {
        this.servMunis = servMunis;
    }

    public CatMunicipiosBean() {
    }

    public CatMunicipios getCurrent() {

        if (current == null) {

            current = new CatMunicipios(new CatMunicipiosPK());
            current.setCatDepartamentos(new CatDepartamentos());

        }

        return current;
    }

    public void setCurrent(CatMunicipios current) {
        this.current = current;
    }

    public CatMunicipios getSearch() {
        if (search == null) {

            search = new CatMunicipios(new CatMunicipiosPK());
            search.setCatDepartamentos(new CatDepartamentos());

        }
        return search;
    }

    public void setSearch(CatMunicipios search) {
        this.search = search;
    }

    public CatMunicipios getSelected() {
        if (selected == null) {

            selected = new CatMunicipios(new CatMunicipiosPK());
            selected.setCatDepartamentos(new CatDepartamentos());

        }
        return selected;
    }

    public void setSelected(CatMunicipios selected) {
        this.selected = selected;
    }

    public List<SelectItem> getItemsDepto() {
        return itemsDepto;
    }

    public void setItemsDepto(List<SelectItem> itemsDepto) {
        this.itemsDepto = itemsDepto;
    }

    public LazyDataModel<CatMunicipios> getGridList() {
        return gridList;
    }

    public void setGridList(LazyDataModel<CatMunicipios> gridList) {
        this.gridList = gridList;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }
}

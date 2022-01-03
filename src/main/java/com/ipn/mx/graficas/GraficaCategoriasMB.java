/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.graficas;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.entidades.Categoria;
import com.ipn.mx.modelo.entidades.Producto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.DonutChartModel;

/**
 *
 * @author leoj_
 */
@ManagedBean(name = "graficaCategoriasMB")
@SessionScoped
public class GraficaCategoriasMB {

    private DonutChartModel categoriasGrafica;

    public GraficaCategoriasMB() {
        createDonutModels();
    }

    public DonutChartModel getCategoriasGrafica() {
        return categoriasGrafica;
    }

    public void setCategoriasGrafica(DonutChartModel categoriasGrafica) {
        this.categoriasGrafica = categoriasGrafica;
    }

    private void createDonutModels() {
        categoriasGrafica = initDonutModel();
        categoriasGrafica.setTitle("");
        categoriasGrafica.setLegendPosition("e");
        categoriasGrafica.setSliceMargin(5);
        categoriasGrafica.setShowDataLabels(true);
        categoriasGrafica.setDataFormat("value");
        categoriasGrafica.setShadow(false);
    }

    private DonutChartModel initDonutModel() {
        DonutChartModel model = new DonutChartModel();
        
        CategoriaDAO daoCat = new CategoriaDAO();
        ProductoDAO daoProd = new ProductoDAO();
        
        List<Categoria> categorias = new ArrayList<>();
        categorias = daoCat.readAll();
        List<Producto> productos = new ArrayList<>();
        productos = daoProd.readAll();
        
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        int cont = 0;
        for (int i = 0; i < categorias.size(); i++) {
            cont = 0;
            Categoria c = categorias.get(i);
            for(int j = 0; j<productos.size(); j++){
                Producto prod = productos.get(j);
                if(c.getIdCategoria().equals(prod.getIdCategoria().getIdCategoria()))
                    cont++;
            }
            if(cont>=0)
                circle1.put(c.getNombreCategoria(), cont);
        }

      
        model.addCircle(circle1);
        return model;
    }

}

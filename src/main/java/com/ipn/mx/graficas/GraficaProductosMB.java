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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DonutChartModel;

/**
 *
 * @author leoj_
 */
@ManagedBean(name = "graficaProductosMB")
@SessionScoped
public class GraficaProductosMB {

    private BarChartModel graficoProductos;

    public GraficaProductosMB() {
        graficoProductos = new BarChartModel();
        initModel();
    }

    public BarChartModel getGraficoProductos() {
        return graficoProductos;
    }

    public void setGraficoProductos(BarChartModel graficoProductos) {
        this.graficoProductos = graficoProductos;
    }
    
    private void initModel() {
        ChartSeries existencias = new ChartSeries();
        existencias.setLabel("Ingresos");

        ProductoDAO daoProd = new ProductoDAO();
        List<Producto> productos = new ArrayList<>();
        productos = daoProd.readAll();

        int max = 0;
        for (int j = 0; j < productos.size(); j++) {
            Producto prod = productos.get(j);
            existencias.set("["+prod.getIdProducto()+"] "+prod.getNombreProducto(), prod.getExistencia());

            if (max < prod.getExistencia()) {
                max = prod.getExistencia();
            }
        }

        graficoProductos.addSeries(existencias);
//        graficoProductos.setTitle("Existencia de Productos");
        graficoProductos.setShowPointLabels(true);

        Axis xAxis = graficoProductos.getAxis(AxisType.X);
        xAxis.setLabel("Producto");

        Axis yAxis = graficoProductos.getAxis(AxisType.Y);
        yAxis.setLabel("Stock");
        yAxis.setMin(0);
        yAxis.setMax(max+10);

        graficoProductos.setSeriesColors("00e0e0");
        graficoProductos.setShadow(false);

    }

}

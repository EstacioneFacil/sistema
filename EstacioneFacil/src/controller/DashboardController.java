package controller;

import dao.AreaDao;
import dao.MovimentacaoDao;
import dao.TipoVeiculoDao;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import static javax.swing.border.TitledBorder.CENTER;
import static javax.swing.border.TitledBorder.DEFAULT_POSITION;
import model.Area;
import model.TipoVeiculo;
import model.constant.DiaSemanaEnum;
import model.util.FormatacaoUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Douglas
 */
public class DashboardController {
    
    private MovimentacaoDao movimentacaoDao;
    
    public DashboardController() {
        movimentacaoDao = new MovimentacaoDao();
    }
    
    //grafico de barras
    public ChartPanel getGraficoMovimentosArea() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        List<Area> areas = new AreaDao().buscarTodos();
        for (Area area : areas) {
            Long total = 0L;
            
            try {
                total = movimentacaoDao.totalPorArea(area.getId()); 
            } catch (Exception e) {
                e.printStackTrace();
            }
            dataset.setValue(total, "Movimentos", area.getDescricao());            
        }
        return criarGraficoBarras(dataset, "Total de movimentos por área");
    }

    public ChartPanel getGraficoMovimentosTipoVeiculo() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        List<TipoVeiculo> tiposVeiculo = new TipoVeiculoDao().buscarTodos();
        for (TipoVeiculo tipoVeiculo : tiposVeiculo) {
            Long total = 0L;
            
            try {
                total = movimentacaoDao.totalPorTipoVeiculo(tipoVeiculo.getId()); 
            } catch (Exception e) {
                e.printStackTrace();
            }
            dataset.setValue(total, "Movimentos", tipoVeiculo.getDescricao());            
        }
        return criarGraficoBarras(dataset, "Total de movimentos por tipo de veículo");
    }
    
    private ChartPanel criarGraficoBarras(CategoryDataset dataset, String titulo) {
        JFreeChart barChart = ChartFactory.createBarChart(null, null, "Movimentos", dataset, PlotOrientation.VERTICAL, false, true, false);
        
        barChart.setBackgroundPaint(new Color(255,255,255,0));       
        
        CategoryPlot plotTipoVeiculo = barChart.getCategoryPlot();
        plotTipoVeiculo.setBackgroundPaint(Color.white);
        plotTipoVeiculo.setDomainGridlinePaint(Color.black);
        plotTipoVeiculo.setRangeGridlinePaint(Color.black);              
        
        ChartPanel panelGrafico = new ChartPanel(barChart);
        panelGrafico.setBorder(javax.swing.BorderFactory.createTitledBorder(null, titulo, CENTER, DEFAULT_POSITION, new Font("Arial", Font.PLAIN, 16)));
        
        return panelGrafico;
    }

    
    //grafico de linhas
    public ChartPanel getGraficoValorTotalArea() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        List<Area> areas = new AreaDao().buscarTodos();
        for (Area area : areas) {
            XYSeries series = new XYSeries(area.getDescricao());
            
            for (int i = 0; i < 12; i++) {
                Double total = 0.0;
                try {
                    Date dataInicial = FormatacaoUtils.getData(FormatacaoUtils.getPrimeiroDiaDoMes(i));
                    Date dataFinal = FormatacaoUtils.getData(FormatacaoUtils.getUltimoDiaDoMes(i));
                    total = movimentacaoDao.valorTotalPorAreaMes(area.getId(), dataInicial, dataFinal); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
                series.add(i, total != null ? total : 0);
            }
            dataset.addSeries(series);
        }
        return criarGraficoLinha(dataset, "Valor total por área");
    }
    
    private ChartPanel criarGraficoLinha(XYDataset dataset, String titulo) {
        JFreeChart xyChart = ChartFactory.createXYLineChart(null, null, "Valor (R$)", dataset, PlotOrientation.VERTICAL, true, true, false);
        xyChart.setBackgroundPaint(new Color(255,255,255,0));
        
        XYPlot plot = xyChart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setBaseToolTipGenerator(xyToolTipGenerator);
        plot.setRenderer(renderer);
              
        //monta meses
        String[] meses = new String[DiaSemanaEnum.values().length];
        for (int i = 0; i < DiaSemanaEnum.values().length; i++) {
            meses[i] = DiaSemanaEnum.getByKey(i).getDescricao();
        }
        SymbolAxis rangeAxis = new SymbolAxis(null, meses);
        rangeAxis.setGridBandsVisible(false);
        plot.setDomainAxis(rangeAxis); 
        
        ChartPanel panelGrafico = new ChartPanel(xyChart);
        panelGrafico.setBorder(javax.swing.BorderFactory.createTitledBorder(null, titulo, CENTER, DEFAULT_POSITION, new Font("Arial", Font.PLAIN, 16)));
        
        return panelGrafico;
    }
    
    
    XYToolTipGenerator xyToolTipGenerator = new XYToolTipGenerator() {
        public String generateToolTip(XYDataset dataset, int series, int item) {
            Number x1 = dataset.getX(series, item);
            Number y1 = dataset.getY(series, item);
            return "R$: "+FormatacaoUtils.getValorFormatado(y1.doubleValue())+" ("+dataset.getSeriesKey(series)+" - "+DiaSemanaEnum.getByKey(x1.intValue()).getDescricao()+")";
        }
    };
}

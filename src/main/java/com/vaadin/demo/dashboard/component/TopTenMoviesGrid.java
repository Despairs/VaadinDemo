package com.vaadin.demo.dashboard.component;

import com.vaadin.data.provider.ListDataProvider;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.domain.MovieRevenue;
import com.vaadin.ui.Grid;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Table;

@SuppressWarnings("serial")
public final class TopTenMoviesGrid extends Grid<MovieRevenue> {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public TopTenMoviesGrid() {
        setCaption("Top 10 Titles by Revenue");

        setSizeFull();
        setHeaderVisible(false);
        setSelectionMode(SelectionMode.NONE);

        List<MovieRevenue> movieRevenues = new ArrayList<>(DashboardUI.getDataProvider().getTotalMovieRevenues());
        Collections.sort(movieRevenues, (final MovieRevenue o1, final MovieRevenue o2) -> o2.getRevenue().compareTo(o1.getRevenue()));
        
        addColumn(movie -> movieRevenues.lastIndexOf(movie) + 1)
                .setStyleGenerator(item -> "v-align-center")
                .setSortable(false);

        addColumn(MovieRevenue::getTitle)
                .setExpandRatio(2)
                .setSortable(false);

        addColumn(movie -> "$" + decimalFormat.format(movie.getRevenue()))
                .setStyleGenerator(item -> "v-align-right")
                .setExpandRatio(1)
                .setSortable(false);

        ListDataProvider<MovieRevenue> dataProvider = com.vaadin.data.provider.DataProvider.ofCollection(movieRevenues.subList(0, 10));
        setDataProvider(dataProvider);

    }

}

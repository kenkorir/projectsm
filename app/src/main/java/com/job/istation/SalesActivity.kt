package com.job.istation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.job.istation.commoners.AppUtils.setDrawable
import com.job.istation.commoners.BaseActivity
import com.job.istation.commoners.ChartLabelsFormatter
import com.job.istation.commoners.TimeFormatter
import com.job.istation.commoners.hideView
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment
import com.mikepenz.ionicons_typeface_library.Ionicons
import kotlinx.android.synthetic.main.activity_sales.*
import java.util.*


class SalesActivity : BaseActivity(), SmoothDateRangePickerFragment.OnDateRangeSetListener {
    private lateinit var dateRangePicker: SmoothDateRangePickerFragment
    private var startMonth = 0
    private var endMonth = 0

    var carmonth = emptyArray<String>()

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, SalesActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        initViews()

        showLoading("Loading records...")
        Handler().postDelayed({hideLoading()}, 2000)

        totalSales()
        carSalesBar()
        partSales()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sales Reports"

        dateFilter.setImageDrawable(setDrawable(this, Ionicons.Icon.ion_android_calendar, R.color.textGray, 27))
        date.text = "Report for: Jan 01, 2018 - ${TimeFormatter().getReportTime(System.currentTimeMillis())}"
        dateFilter.hideView()

        dateRangePicker = SmoothDateRangePickerFragment.newInstance(this)
        dateRangePicker.maxDate = Calendar.getInstance()

        dateFilter.setOnClickListener { dateRangePicker.show(fragmentManager, "") }
    }

    private fun totalSales() {
        val salesEntries = mutableListOf<Entry>()

        val toy1 = Entry(0f,800000f)
        salesEntries.add(toy1)
        val toy2 = Entry(1f,950000f)
        salesEntries.add(toy2)
        val toy3 = Entry(2f,1200000f)
        salesEntries.add(toy3)
        val toy4 = Entry(3f,750000f)
        salesEntries.add(toy4)
        val nis1 = Entry(4f,1300000f)
        salesEntries.add(nis1)
        val nis2 = Entry(5f,670000f)
        salesEntries.add(nis2)
        val nis3 = Entry(6f,840000f)
        salesEntries.add(nis3)

        val salesDataSet = LineDataSet(salesEntries, "SALES (KES 25,900,000)")
        salesDataSet.axisDependency = YAxis.AxisDependency.LEFT
        salesDataSet.color = ColorTemplate.COLORFUL_COLORS.asList().random()!!

        val dataSets = mutableListOf<ILineDataSet>()
        dataSets.add(salesDataSet)

        val lineData = LineData(dataSets)
        totalSales.data = lineData
        totalSales.description = null
        totalSales.setDrawGridBackground(false)
        totalSales.setDrawBorders(false)
        totalSales.setDrawMarkers(false)
        totalSales.axisRight.setDrawLabels(false)
        totalSales.axisLeft.setDrawLabels(false)

        val labels = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul")

        val xAxis = totalSales.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.valueFormatter = ChartLabelsFormatter(labels)

        val yAxisRight = totalSales.axisRight
        yAxisRight.setDrawLabels(false)
        yAxisRight.setDrawGridLines(false)

        totalSales.invalidate()
    }

    private fun carSalesBar() {
        val entries = mutableListOf<BarEntry>()
        entries.add(BarEntry(0f, 15f))
        entries.add(BarEntry(1f, 9f))
        entries.add(BarEntry(2f, 5f))
        entries.add(BarEntry(3f, 1f))
        entries.add(BarEntry(4f, 2f))
        entries.add(BarEntry(5f, 3f))

        val labels = arrayOf("Diesel", "petrol", "Kerosine", "Auto", "Unleaded","Leaded")

        val set = BarDataSet(entries, "Sales (Units)")
        set.colors = ColorTemplate.COLORFUL_COLORS.asList()

        val data = BarData(set)
        carSalesBar.data = data
        carSalesBar.setFitBars(true)
        carSalesBar.description = null

        val xAxis = carSalesBar.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = ChartLabelsFormatter(labels)
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true

        val yAxisRight = carSalesBar.axisRight
        yAxisRight.setDrawLabels(false)
        yAxisRight.setDrawGridLines(false)

        carSalesBar.invalidate()
    }

    private fun partSales() {
        val entries = mutableListOf<BarEntry>()
        entries.add(BarEntry(0f, 32f))
        entries.add(BarEntry(1f, 87f))
        entries.add(BarEntry(2f, 42f))
        entries.add(BarEntry(3f, 51f))

        val labels = arrayOf("November", "December", "January", "February")

        val set = BarDataSet(entries, "Sales (Quantity)")
        set.colors = ColorTemplate.COLORFUL_COLORS.asList()

        val data = BarData(set)
        partSalesBar.data = data
        partSalesBar.setFitBars(true)
        partSalesBar.description = null

        val xAxis = partSalesBar.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = ChartLabelsFormatter(labels)
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true

        val yAxisRight = partSalesBar.axisRight
        yAxisRight.setDrawLabels(false)
        yAxisRight.setDrawGridLines(false)

        partSalesBar.invalidate()
    }

    override fun onDateRangeSet(view: SmoothDateRangePickerFragment?, yearStart: Int, monthStart: Int, dayStart: Int, yearEnd: Int, monthEnd: Int, dayEnd: Int) {
        var months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

        date.text = "${months[monthStart]} $dayStart, $yearStart - ${months[monthEnd]} $dayEnd, $yearEnd"
        startMonth = monthStart
        endMonth = monthEnd

        for (i in startMonth..endMonth) {
            carmonth.set(carmonth.size, months[i])
        }

        //loadData()
    }

    private fun loadData() {

        showLoading("Loading reports...")
        Handler().postDelayed({

            Toast.makeText(this,"Reports are ready",Toast.LENGTH_LONG).show()
            hideLoading()
        },2000)
    }
}

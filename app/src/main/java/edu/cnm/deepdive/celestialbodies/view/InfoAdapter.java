package edu.cnm.deepdive.celestialbodies.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import java.text.DateFormat;
import java.util.List;

public class InfoAdapter extends ArrayAdapter<Star> {

  DateFormat dateFormat2;
  String starIdFormat2;

  /**
   * <code>HistoryAdapter</code> provides a link between the database and UI display. Using the
   * the available categories within the {@link Star} entity as a reference to make the columns
   * (date and score) that are displayed when {@link InfoFragment} is inflated for display for the
   * user.
   *
   * @param context access to the database
   * @param items categories from Consumption (date, score)
   */

  public InfoAdapter(@NonNull Context context, List<Star> items) {
    super(context, R.layout.info_item, items);
    dateFormat2 = android.text.format.DateFormat.getDateFormat(context);
    starIdFormat2 = context.getString(R.string.star_format);
  }


  /**
   * <code>getView</code> converts the database items within {@link Star} entity class to
   * be viewed as a text.
   *
   * @param position position of each item.
   * @param convertView converts view.
   * @param parent group of Consumption
   * @return visual text of the items
   */
  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View view = (convertView != null) ? convertView
        : LayoutInflater.from(getContext()).inflate(R.layout.info_item, parent, false);
    Star star2 = getItem(position);
    TextView row = view.findViewById(R.id.row_item);
    //TextView date2 = view.findViewById(R.id.date_item);
    TextView starName = view.findViewById(R.id.star_item);
    row.setText(star2.getId());
    // date2.setText(dateFormat2.format(star2.getTime().getTime()));
    starName.setText(star2.getCatId());

    return view;
  }

//  @Nullable
//  @TypeConverter
//  public static Calendar calendarFromLong(@Nullable Long milliseconds) {
//    if (milliseconds != null) {
//      Calendar calendar = Calendar.getInstance();
//      calendar.setTimeInMillis(milliseconds);
//      return calendar;
//    }
//    return null;
//
//
//  }

}

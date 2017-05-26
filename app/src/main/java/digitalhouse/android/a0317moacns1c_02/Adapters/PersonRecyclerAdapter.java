package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Entities.PersonListItem;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by Pablo on 25/05/2017.
 */

public class PersonRecyclerAdapter extends Adapter {
    private Context context;
    private List<PersonListItem> personList;

    public PersonRecyclerAdapter(Context context, List<PersonListItem> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View cellView = inflater.inflate(R.layout.cell_credit_list, parent, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(cellView);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PersonListItem person = personList.get(position);
        PersonViewHolder personViewHolder = (PersonViewHolder) holder;
        personViewHolder.name.setText(person.getName());
        personViewHolder.role.setText(person.getRole());
        Picasso.with(context).load(person.getProfileURL()).fit().centerCrop().into(personViewHolder.picture);

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    private class PersonViewHolder extends RecyclerView.ViewHolder {
        private ImageView picture;
        private TextView name;
        private TextView role;
        private PersonViewHolder(View view) {
            super(view);
            picture = (ImageView) view.findViewById(R.id.imageViewCreditsPicture);
            name = (TextView) view.findViewById(R.id.textViewCreditsName);
            role = (TextView) view.findViewById(R.id.textViewCreditsCharacter);
        }
    }
}

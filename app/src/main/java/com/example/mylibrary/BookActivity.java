
package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private ImageView imgBookIcon;
    private Button btnCurrentlyReading, btnAddToWhatToRead, btnAddToAlreadyRead, btnAddToFavorite;
    private TextView txtSetBookName, txtSetAuthorName, txtSetPages, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();


    /*    String longDesc="Idries Shah wrote a series of charming and captivating stories for his own children. " + "\n" +
                "These are being produced by the Idries Shah Foundation as richly-illustrated children’s books. So far, the series comprises six titles – including Speak First and Lose, The Tale of the Sands," +"\n"+
                " and The Onion – and six more titles will join the collection in 2022." +"\n"+
                " Our aim is to inspire a whole new generation with these stories and provide them for free to children in deprived societies.";

        //TODO: Get the data from recycler view in here

        Books book = new Books(1, "The Subtle Art Of Not Giving A FUCK ", "Mark Manson", 150, "https://anylang.net/sites/default/files/covers/i654912.jpg",
                "a book that will help you!", longDesc);*/

        Intent intent = getIntent();

        if (null != intent) {
            int bookId = intent.getIntExtra("bookId", -1);
            if (bookId != -1) {
                Books incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBook(incomingBook);
                    handleCurrentlyReadingBook(incomingBook);
                    handleFavoriteBook(incomingBook);
                }
            }
        }

    }

    /**
     * Enable and Disable Buttons
     * Add to AlreadyReadBooks array
     *
     * @param book
     */
    private void handleAlreadyRead(Books book) {
        ArrayList<Books> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Books b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }
        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToAlreadRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavoriteBook(Books book){
        ArrayList<Books> FavoriteBooks = Utils.getInstance().getFavoriteBooks();

        boolean existInFavoriteBooks = false;

        for (Books b : FavoriteBooks) {
            if (b.getId() == book.getId()) {
                existInFavoriteBooks = true;
            }
        }
        if (existInFavoriteBooks) {
            btnAddToFavorite.setEnabled(false);
        } else {
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToFavorite(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BookActivity.this,FavoriteBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void handleCurrentlyReadingBook(Books book) {
        ArrayList<Books> currentlyReadingBooks = Utils.getInstance().getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Books b : currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
            }
        }
        if (existInCurrentlyReadingBooks) {
            btnCurrentlyReading.setEnabled(false);
        } else {
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BookActivity.this,CurrentlyReadingBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBook(Books book) {
        ArrayList<Books> wantToRead = Utils.getInstance().getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Books b : wantToRead) {
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
            }
        }
        if (existInWantToReadBooks) {
            btnAddToWhatToRead.setEnabled(false);
        } else {
            btnAddToWhatToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToWishlist(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Books book) {
        txtSetBookName.setText(book.getName());
        txtSetAuthorName.setText(book.getAuthor());
        txtSetPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImgURL())
                .into(imgBookIcon);
    }

    private void initViews() {

        imgBookIcon = findViewById(R.id.imgbookicon);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAddToWhatToRead = findViewById(R.id.btnAddToWishlist);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreaduRead);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);
        txtSetBookName = findViewById(R.id.txtSetBookName);
        txtSetAuthorName = findViewById(R.id.txtSetAuthorName);
        txtSetPages = findViewById(R.id.txtSetPages);
        txtDescription = findViewById(R.id.txtLongDescription);

    }
}
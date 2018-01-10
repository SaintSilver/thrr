package thrr.asmr.finalproject.com.thrr;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class SetActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private Button logout_btn, advice_btn, delete_btn;
    private SharedPreferences spf;
    private static final String TAG = "SetActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        logout_btn = (Button) findViewById(R.id.btn_logout);
        delete_btn = (Button) findViewById(R.id.btn_backup);
        advice_btn = (Button) findViewById(R.id.btn_advice);
        logout_btn.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/letter.ttf"));
        delete_btn.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/letter.ttf"));
        advice_btn.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/letter.ttf"));

        // DELETE BUTTON
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spf = getSharedPreferences("emailspf", MODE_PRIVATE);
                String email = spf.getString("email", "");

                    Log.v("데이터삭제 요청 email :::::::", email + "");
                    /* =================서버접속해서 email에 해당하는 DB값 삭제=============== */
                    String sendmsg = "vision_delete";
                    try{
                        new Task(sendmsg).execute("7",email).get();//보내는것
                        Task task = new Task();
                        String result = task.receiveMsg;
                        Log.v("결과:",result);
                        if(result.equals("false")) {
                            Log.v("데이터삭제 오류 ::::::", "email이 유효하지 않음:::" + email);
                            Toast.makeText(getApplicationContext(), "오류가 발생했어요.", Toast.LENGTH_LONG).show();
                        }else if(result.equals("true")){
                            Toast.makeText(getApplicationContext(), "데이터 삭제가 완료되었습니다.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SetActivity.this, introActivity.class));;
                            finish();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
        });


        // ADVICE BUTTON
        advice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        // LOGOUT BUTTON
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        // [START configure_signin]
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]

    } //end of onCreate()

    //다이얼로그 메소드
    void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("도움말");
        builder.setMessage("어플리케이션 소개\n" +
                "반갑습니다, 이용자 여러분.\n" +
                "이 앱은 ASMR을 이용하여 여러분의 수면 환경 및 집중력에 도움을 주고자 만들어진 프로그램입니다.\n" +
                "\n" +
                "ASMR이란? Autonomous Sensory Meridian Response 이라는 단어의 줄임말로, 자율 감각 쾌감 작용이라고도 불립니다. 제니퍼 앨런(Jennifer Allen)이라는 사람에 의해 정의된 현상으로서, ‘심리적 안정감이나 쾌감을 주는 특정 소리 또는 현상’을 뜻합니다.\n" +
                " \n" +
                "주요 기능\n" +
                "ASMR 플레이어 : 5개의 테마 및 각 테마 별 10가지의 소리가 등록되어 있으며, 최대 3개의 소리를 중첩시켜 들을 수 있습니다. 이 기능은 수면 기능과 집중 기능에 포함되어 있습니다.\n" +
                "수면 기능 : ASMR을 들으며 편안한 휴식을 맞이하세요. 자체 제작된 알람을 제공합니다.\n" +
                "집중 기능 : 1가지 일에 집중하지 못하고 자꾸만 다른 짓이 하고 싶어지는 당신! 이 기능을 통해 최대한의 집중력을 느껴보세요. 작업 중 다른 쪽으로 새는 분을 위하여 타이머 및 화면 터치 불가 기능이 탑재되어 있습니다. 통화기능은 잠그지 않았으니 급한 연락도 걱정마세요! \n" +
                "분석 기능 : 여러분이 선택한 ASMR, 주위 환경을 분석하여 각종 지표를 제공합니다. \n" +
                "\n" +
                "차후 업데이트 될 기능 ; \n" +
                "심박 탐지 기능 제품, 추가 기능, 음원 등을 업데이트할 예정입니다.\n" +
                "좋은 하루 되세요! ");
        builder.setPositiveButton("알겠어요!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
// [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //String email = acct.getEmail();
        } else {
            // Signed out, show unauthenticated UI.
        }
    }
// [END handleSignInResult]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        spf = getSharedPreferences("emailspf", MODE_PRIVATE);
                        String loginEmail = spf.getString("email", "");
                        Log.v("로그인되어있는 이메일: ", loginEmail);
                        spf.edit().remove("email").commit();
                        spf = null;
                        Intent intent = new Intent(SetActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
}
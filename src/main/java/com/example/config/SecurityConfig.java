package com.example.config;


//ハッシュ用
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//どこまで入って良いかのライン
		http.authorizeRequests().antMatchers("/loginForm").permitAll() //ログインフォームは万物OK
		.anyRequest().authenticated(); //他のところはログインしてなきゃログインを求める
		
		//ログインの処理
		http.formLogin()//開始
		.loginProcessingUrl("/login") // ログイン処理のためのパス
		.loginPage("/loginForm") // ログインページはloginForm指定
		.usernameParameter("email") // ログインフォームで使うメールアドレス
		.passwordParameter("password") // ログインフォームで使うパスワード
		.defaultSuccessUrl("/library", true) // ログイン成功したらlibraryに行く
		.failureUrl("/loginForm?error"); // ログイン失敗したらエラーのページへGO
	
		http.logout()
		.logoutUrl("/logout") //ログアウト処理するためのパス
		.logoutSuccessUrl("/loginForm"); //ログアウトしたらログインフォームへ帰る
	}
	
	// ハッシュ化
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	// WebSecurity型の引数を持ったconfigure()を追加します
	public void configure(WebSecurity web) throws Exception {
		/** 以下のファイルパス配下のディレクトリ、ファイルすべてを認証・認可の対象から除外します
            src/main/resources/static/css/
            src/main/resources/static/js/
		 */
		web.ignoring().antMatchers("/css/**", "/js/**");
	}
}

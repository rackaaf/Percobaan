package com.example.ewaste.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ewaste.ui.screen.ChangePasswordScreen
import com.example.ewaste.ui.screen.ForgotPasswordScreen
import com.example.ewaste.ui.screen.HomeScreen
import com.example.ewaste.ui.screen.JenisScreen
import com.example.ewaste.ui.screen.KategoriScreen
import com.example.ewaste.ui.screen.LoginScreen
import com.example.ewaste.ui.screen.OtpScreen
import com.example.ewaste.ui.screen.ProfileScreen
import com.example.ewaste.ui.screen.RegisterScreen
import com.example.ewaste.ui.screen.ResetPasswordScreen
import com.example.ewaste.ui.screen.SplashScreen

@Composable
fun EWasteApp() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("register") { RegisterScreen(navController) }
//        composable("otp") { OtpScreen(navController) }
        composable("otp/{purpose}") { backStackEntry ->
            val purpose = backStackEntry.arguments?.getString("purpose") ?: "register"
            OtpScreen(navController, purpose)
        }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("forgot") { ForgotPasswordScreen(navController) }
        composable("reset") { ResetPasswordScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("change-password") { ChangePasswordScreen(navController) }
        composable("kategori") { KategoriScreen(navController) }
        composable("jenis") { JenisScreen(navController) }

    }
}

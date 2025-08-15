//
//  iosAppApp.swift
//  iosApp
//
//  Created by 허희태 on 8/7/25.
//

import SwiftUI
import sharedModuleKit

@main
struct iosAppApp: App {
    init() {
        IosKoinStarter.shared.start()
    }


    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

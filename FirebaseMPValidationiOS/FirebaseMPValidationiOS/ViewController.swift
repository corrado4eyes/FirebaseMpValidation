//
//  ViewController.swift
//  FirebaseMPValidationiOS
//
//  Created by Corrado Quattrocchi on 13/08/2020.
//  Copyright © 2020 Corrado Quattrocchi. All rights reserved.
//

import UIKit
import shared

class ViewController: UIViewController {

    private lazy var nativeViewModel: NativeViewModel = {
        return NativeViewModel(onMeetingDataReceived: {[weak self] result in self?.onMeetingDataReceived(meetingData: result)})
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        print(ExpectKt.createScreenMessage())
        print(nativeViewModel.getDocument(documentPath: "meetings/meeting"))
    }

    
    private func onMeetingDataReceived(meetingData: MeetingData) {
        print(meetingData)
    }

}


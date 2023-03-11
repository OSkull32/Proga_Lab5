for i in range(1, 10000):
    fileName = "scr" + str(i) + ".txt"
    with open(fileName, 'w+') as f:
        f.write("history\n execute_script test-files/scr" + str(i+1) + ".txt\n save")
    
import javazoom.jl.player.Player

import java.awt.Color
import java.io.{File, FileInputStream}
import javax.sound.sampled.AudioSystem
import javax.swing.{JSpinner, SpinnerNumberModel}
import scala.swing.MenuBar.NoMenuBar.contents
import scala.swing.event.ButtonClicked
import scala.swing.{BoxPanel, Button, ComboBox, Component, Dimension, Label, MainFrame, Orientation, SimpleSwingApplication, SplitPane}
//import scala.sys.process.processInternal.File

object MainApp extends SimpleSwingApplication {

  def top = new MainFrame() {


    val ballColorChoices = Seq("Czerowny", "Zielony", "Niebieski")
    val ballColorChooser = new ComboBox(ballColorChoices)
    val gamePanel = new Game
    val spinnerModel = new SpinnerNumberModel(1, 1, 100, 1) // Domyślnie 1, min 1, max 100, krok 1
    val numberOfBallsSpinner = new Component {
      override lazy val peer: JSpinner = new JSpinner(spinnerModel)
    }

    val musicButton = new Button("Music") {
      reactions += {
        case ButtonClicked(_) =>
          playMusic("C:\\Users\\jpora\\OneDrive\\Pulpit\\Michal\\scala_studia\\BouncingBallScala\\BouncingBallsScala\\the-rhythm-of-the-africa-wav-8837.mp3") // Podaj ścieżkę do pliku muzycznego
      }
    }

    val startButton = new Button("Start Gry") {
      reactions += {
        case ButtonClicked(_) =>
          val selectedColor = ballColorChooser.selection.item match
            case "Czerowny" => Color.RED
            case "Zielony" => Color.GREEN
            case "Niebieski" => Color.BLUE
          val number = spinnerModel.getValue.asInstanceOf[Int]
          gamePanel.start(number, selectedColor)

      }
    }

    val controlsPanel = new BoxPanel(Orientation.Vertical) {
      contents += new Label("Liczba piłek:")
      contents += numberOfBallsSpinner
      contents += new Label("Kolor piłki:")
      contents += ballColorChooser
      contents += startButton
      contents += musicButton
    }

    def playMusic(filePath: String): Unit = {
      val fis = new FileInputStream(filePath)
      val player = new Player(fis)
      val musicThread = new Thread(() => {
        try player.play()
        catch {
          case e: Exception => e.printStackTrace()
        }
      })
      musicThread.start()
    }

    val mainPanel = new SplitPane(Orientation.Vertical, controlsPanel,gamePanel) {
      continuousLayout = true
      dividerLocation = 150
    }

    size = new Dimension(800, 800)
    contents = mainPanel
  }
}

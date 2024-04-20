import { useEffect, useState } from "react";
import { ImCross } from "react-icons/im";
import { RiArrowLeftSLine, RiArrowRightSLine } from "react-icons/ri";
import PetDetailsInfo from "./PetDetailsInfo";
import ReportPet from "./ReportPet";

export default function PetDetails({ isOpen, onClose, pet }) {
  const [activeIndex, setActiveIndex] = useState(0);

  useEffect(() => {
    if (isOpen) {
      document.body.classList.add("modal-open");
    } else {
      document.body.classList.remove("modal-open");
    }

    return () => {
      document.body.classList.remove("modal-open");
    };
  }, [isOpen]);

  const nextSlide = () => {
    setActiveIndex((prevIndex) => (prevIndex + 1) % 2);
  };

  const prevSlide = () => {
    setActiveIndex((prevIndex) => (prevIndex - 1 + 2) % 2);
  };

  return (
    <>
      {isOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 backdrop-filter backdrop-blur-sm z-40">
          <div className="fixed z-50 top-1/2 md:left-1/2 left-0 transform md:-translate-x-1/2 md:-translate-y-1/2 bg-white p-6 rounded-md">
            <div className="flex justify-end">
              <button onClick={onClose} className="p-2 text-black">
                <ImCross />
              </button>
            </div>
            <div className="relative">
              {activeIndex === 0 && <ReportPet pet={pet} />}
              {activeIndex === 1 && <PetDetailsInfo pet={pet} />}
              <button
                onClick={prevSlide}
                className="absolute top-1/2 left-1 transform -translate-y-1/2 font-bold text-primary_colour text-5xl bg-gray-200 rounded-full"
              >
                <RiArrowLeftSLine />
              </button>
              <button
                onClick={nextSlide}
                className="absolute top-1/2 right-1 transform -translate-y-1/2 font-bold text-primary_colour text-5xl bg-gray-200 rounded-full"
              >
                <RiArrowRightSLine />
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
